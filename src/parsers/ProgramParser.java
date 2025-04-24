package parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.collections4.SetUtils;
import parsers.schema.EnvDetail;
import parsers.schema.EnvType;
import parsers.schema.Result;
import parsers.schema.Size;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static parsers.schema.EnvType.DEV;
import static parsers.schema.EnvType.PROD;
import static parsers.schema.EnvType.STAGE;

public class ProgramParser {

    private static final Pattern envPattern = Pattern.compile("cm-p(\\d+)-e(\\d+)");
    private static final EnumSet<EnvType> ENUM_SET = EnumSet.of(PROD, DEV, STAGE);

    public static void main(String[] args) throws IOException {
        final String enableEnvs = "enable_env_output.txt";
        final String disableEnvs = "disable_env_output.txt";


        Map<String, List<EnvDetail>> programIdEnvDetailMap;
        Set<String> programIdSet;
        Map<String, Double> sizeByServiceSmall;
        Map<String, Double> sizeByServiceBig;

        try (final Stream<String> lines = Files.lines(Path.of("all_envs.txt"))) {

            programIdEnvDetailMap = lines.map(l -> {
                String[] s = l.split(" ");
                return new EnvDetail(s[4], s[3], s[1], s[2]);
            }).filter(envDetail -> envPattern.matcher(envDetail.envId()).matches()).collect(Collectors.groupingBy(envDetail -> {
                Matcher matcher = envPattern.matcher(envDetail.envId());
                matcher.matches(); // safe to execute
                return matcher.group(1);
            }, LinkedHashMap::new, Collectors.toList()));
        }

        try(final Stream<String> lines = Files.lines(Path.of("programId.txt"))) {
            programIdSet = lines.filter(ProgramParser::isInteger).map(String::trim).collect(Collectors.toSet());
        }

        try (InputStream inputStream = Files.newInputStream(Path.of("storageSize.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Size size = objectMapper.readValue(inputStream, Size.class);

            // Process the results
            final Map<String, Map<String, Double>> collect = size.data().result().stream()
                    .collect(Collectors.teeing(
                            Collectors.filtering(lowerThan70, Collectors.toMap(
                                    result -> result.metric().aemService(),
                                    result -> Double.parseDouble(result.value()[1].toString())
                            )),
                            Collectors.filtering(greaterThan70, Collectors.toMap(
                                    result -> result.metric().aemService(),
                                    result -> Double.parseDouble(result.value()[1].toString())
                            )),
                            (prodList, nonProdList) -> Map.of("small", prodList, "big", nonProdList)
                    ));
            // Use the data
            sizeByServiceSmall = collect.get("small");
            sizeByServiceBig = collect.get("big");
//            sizeByServiceSmall.forEach((service, s) -> System.out.printf("%s: %.2f GB%n", service, s / (1024 * 1024 * 1024)));
//            sizeByServiceBig.forEach((service, s) -> System.out.printf("%s: %.2f GB%n", service, s / (1024 * 1024 * 1024)));
            System.out.println("Small Size: " + sizeByServiceSmall.size());
            System.out.println("Big Size: " + sizeByServiceBig.size());
        }

        System.out.println("Missing Program Data : " + SetUtils.difference(programIdSet, programIdEnvDetailMap.keySet()));

        final List<String> lines = ReadUtils.readEnvFiles("disable_env_output.txt").stream().filter(l -> l.startsWith("ethos")).toList();
        final Map<String, List<EnvDetail>> exitingBigEnvs = lines.stream().map(l -> {
            String[] s = l.split(" ");
            return new EnvDetail(s[3], s[2], s[0], s[1]);
        }).filter(envDetail -> envPattern.matcher(envDetail.envId()).matches()).collect(Collectors.groupingBy(envDetail -> {
            Matcher matcher = envPattern.matcher(envDetail.envId());
            matcher.matches(); // safe to execute
            return matcher.group(1);
        }, LinkedHashMap::new, Collectors.toList()));
//        System.out.println(exitingBigEnvs);
        final Map<String, Set<EnvDetail>> bigEnvs = LinkedHashMap.newLinkedHashMap(100);
        exitingBigEnvs.forEach((k, v) -> bigEnvs.computeIfAbsent(k, k1 -> new LinkedHashSet<>()).addAll(v));

//        System.out.println(bigEnvs);

        final Map<String, Set<EnvDetail>> smallEnvs = LinkedHashMap.newLinkedHashMap(500);

        try (FileWriter enableEnvWriter = new FileWriter(enableEnvs); FileWriter disableEnvWriter = new FileWriter(disableEnvs)) {
            programIdEnvDetailMap.forEach((key, value) -> {
                if (!programIdSet.contains(key)) {
                    return;
                }
                // Single traversal of environments
                for (EnvDetail env : value) {
                    EnvType envType = EnvType.fromString(env.type());
                    if (!ENUM_SET.contains(envType)) {
                        continue;
                    }

                    if (sizeByServiceSmall.containsKey(env.envId())) {
                        smallEnvs.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(env);
                    }

                    if (sizeByServiceBig.containsKey(env.envId())) {
                        bigEnvs.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(env);
                    }
                }
            });
            System.out.println("Big Envs: " + bigEnvs.size());
            System.out.println("Small Envs: " + smallEnvs.size());
            writeEnvs(enableEnvWriter, smallEnvs, sizeByServiceSmall);
            writeEnvs(disableEnvWriter, bigEnvs, new ImmutableMap.Builder<String, Double>()
                    .putAll(sizeByServiceSmall)
                    .putAll(sizeByServiceBig)
                    .build());
        }
    }

    // helper methods

    private static final Predicate<Result> lowerThan70 = r -> compareWith70(r) < 0;

    private static final Predicate<Result> greaterThan70 = r -> compareWith70(r) > 0;

    private static int compareWith70(Result r) {
        return Double.compare(Double.parseDouble(r.value()[1].toString()) / (1024 * 1024 * 1024), 70.00d);
    }

    private static void writeEnvs(final FileWriter fileWriter, final Map<String, Set<EnvDetail>> envDetailMap, Map<String, Double> sizeByService) throws IOException {

        fileWriter.write("\n");
        envDetailMap.forEach((key, value) -> {
            try {
                fileWriter.write("PROGRAM ID : " + key);
                fileWriter.write("\n");
                fileWriter.write("\n");
                value.forEach(e -> {
                    try {
                        fileWriter.write(e.toString() + " " + Math.ceil((sizeByService.get(e.envId()) / (1024 * 1024 * 1024) * 100)) / 100);
                        fileWriter.write("\n");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                });
                fileWriter.write("\n");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Not an integer : " + str);
            return false;
        }
    }
}
