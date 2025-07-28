package parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import parsers.schema.EnvDetail;
import parsers.schema.EnvType;
import parsers.schema.Result;
import parsers.schema.Size;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static parsers.schema.EnvType.DEV;
import static parsers.schema.EnvType.PROD;
import static parsers.schema.EnvType.STAGE;

public class ReadUtils {

    private ReadUtils() {
    }

    public static final Pattern ENV_PATTERN = Pattern.compile("cm-p(\\d+)-e(\\d+)");
    public static final Pattern PROGRAM_PATTERN = Pattern.compile("\\d+");
    public static final EnumSet<EnvType> ENUM_SET = EnumSet.of(PROD, DEV, STAGE);

    public static Set<String> getAemServiceSet(String... fileNames) {

        final List<String> lines = readEnvFiles(fileNames);

        return lines.stream().filter(s -> s.startsWith("ethos")).map(s -> s.split(" ")[2]).collect(Collectors.toSet());
    }

    public static Set<String> getAllAemServiceSet(String fileName) throws IOException {

        Set<String> allAemServiceSet;

        try (final Stream<String> lines = Files.lines(Path.of(fileName))) {
            allAemServiceSet = getEnvDetailStream(lines).map(EnvDetail::envId).
                    filter(input -> ENV_PATTERN.matcher(input).matches())
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        }
        return allAemServiceSet;
    }

    public static Map<String, EnvDetail> getEnvDetailSet(String fileName) throws IOException {
        try (final Stream<String> lines = Files.lines(Path.of(fileName))) {
            return getEnvDetailStream(lines)
                    .filter(input -> ENV_PATTERN.matcher(input.envId()).matches())
                    .collect(Collectors.toMap(
                            EnvDetail::envId,
                            Function.identity(),
                            ReadUtils::keepFirst,
                            LinkedHashMap::new
                    ));
        }
    }

    private static Stream<EnvDetail> getEnvDetailStream(Stream<String> lines) {
        return lines.map(l -> {
            String[] s = l.split("\\s+");
            if (s.length < 5) {
                System.out.println(s.length);
                throw new IllegalArgumentException("Invalid line format: " + Arrays.toString(s));
            }
            return new EnvDetail(s[4], s[3], s[1], s[2]);
        });
    }

    @SuppressWarnings("unchecked")
    public static List<String> readEnvFiles(String... fileNames) {
        final Stream<String>[] collect = Arrays.stream(fileNames).map(s -> {
            try {
                return Files.readAllLines(Path.of(s)).stream();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toArray(Stream[]::new);

        // Read the files and collect the lines into a single list
        return Stream.of(collect).flatMap(s -> s).toList();
    }

    public static Map<String, List<EnvDetail>> getProgramIdEnvDetailMap() throws IOException {
        Map<String, List<EnvDetail>> programIdEnvDetailMap;
        try (final Stream<String> lines = Files.lines(Path.of("all_envs_1.txt"))) {
            programIdEnvDetailMap = lines.map(l -> {
                String[] s = l.split("\\s+");
                return new EnvDetail(s[4], s[3], s[1], s[2]);
            }).filter(envDetail -> ReadUtils.ENV_PATTERN.matcher(envDetail.envId()).matches()).collect(Collectors.groupingBy(envDetail -> {
                Matcher matcher = ReadUtils.ENV_PATTERN.matcher(envDetail.envId());
                matcher.matches(); // safe to execute
                return matcher.group(1);
            }, LinkedHashMap::new, Collectors.toList()));
        }
        return programIdEnvDetailMap;
    }

    public static Set<String> getProgramIdSet(String file) throws IOException {
        Set<String> programIdSet;
        try(final Stream<String> lines = Files.lines(Path.of(file))) {
            programIdSet = lines.filter(ReadUtils::isInteger).map(String::trim).collect(Collectors.toSet());
        }
        return programIdSet;
    }

    public static boolean isInteger(String str) {
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

    public static long parseLong(String str) {
        if (str == null || str.isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            System.err.println("Not an Long : " + str);
            return 0L;
        }
    }

    public static double parseDouble(String str) {
        if (str == null || str.isEmpty()) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.err.println("Not an Double : " + str);
            return 0.0d;
        }
    }

    public static void filterBySize(Map<String, Double> sizeByServiceSmall, Predicate<Result> lower, Map<String, Double> sizeByServiceBig, Predicate<Result> greater) throws IOException {

        try (InputStream inputStream = Files.newInputStream(Path.of("storageSize.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Size size = objectMapper.readValue(inputStream, Size.class);

            // Process the results
            final Map<String, Map<String, Double>> collect = size.data().result().stream()
                    .collect(Collectors.teeing(
                            Collectors.filtering(lower, Collectors.toMap(
                                    result -> result.metric().aemService(),
                                    result -> Double.parseDouble(result.value()[1].toString())
                            )),
                            Collectors.filtering(greater, Collectors.toMap(
                                    result -> result.metric().aemService(),
                                    result -> Double.parseDouble(result.value()[1].toString())
                            )),
                            (prodList, nonProdList) -> Map.of("small", prodList, "big", nonProdList)
                    ));
            // Use the data
            sizeByServiceSmall.putAll(collect.get("small"));
            sizeByServiceBig.putAll(collect.get("big"));
//            sizeByServiceSmall.forEach((service, s) -> System.out.printf("%s: %.2f GB%n", service, s / (1024 * 1024 * 1024)));
//            sizeByServiceBig.forEach((service, s) -> System.out.printf("%s: %.2f GB%n", service, s / (1024 * 1024 * 1024)));
        }
    }

    public static void combineBigEnvs(Map<String, Set<EnvDetail>> bigEnvs) {
        final List<String> lines = ReadUtils.readEnvFiles("disable_env_output.txt").stream().filter(l -> l.startsWith("ethos")).toList();
        final Map<String, List<EnvDetail>> exitingBigEnvs = lines.stream().map(l -> {
            String[] s = l.split(" ");
            return new EnvDetail(s[3], s[2], s[0], s[1]);
        }).filter(envDetail -> ReadUtils.ENV_PATTERN.matcher(envDetail.envId()).matches()).collect(Collectors.groupingBy(envDetail -> {
            Matcher matcher = ReadUtils.ENV_PATTERN.matcher(envDetail.envId());
            matcher.matches(); // safe to execute
            return matcher.group(1);
        }, LinkedHashMap::new, Collectors.toList()));
//        System.out.println(exitingBigEnvs);
        exitingBigEnvs.forEach((k, v) -> bigEnvs.computeIfAbsent(k, k1 -> new LinkedHashSet<>()).addAll(v));

//        System.out.println(bigEnvs);
    }

    public static final Predicate<Result> greaterThan200 = r -> compareWith200(r) > 0;

    public static final Predicate<Result> greaterThan70AndLowerThan200 = r -> compareWith70(r) >= 0 && compareWith200(r) < 0;

    public static final Predicate<Result> lowerThan200 = r -> compareWith200(r) < 0;

    public static final Predicate<Result> lowerThan70 = r -> compareWith70(r) < 0;

    public static final Predicate<Result> greaterThan70 = r -> compareWith70(r) > 0;

    private static int compareWith200(Result r) {
        return Double.compare(Double.parseDouble(r.value()[1].toString()) / (1024 * 1024 * 1024), 200.00d);
    }

    private static int compareWith70(Result r) {
        return Double.compare(Double.parseDouble(r.value()[1].toString()) / (1024 * 1024 * 1024), 70.00d);
    }

    private static EnvDetail keepFirst(EnvDetail e1, EnvDetail e2) {
        return e1; // If there are duplicates, keep the first one
    }
}
