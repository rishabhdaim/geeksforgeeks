package parsers.fullgc;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import parsers.ReadUtils;
import parsers.fullgc.schema.EnvDetail;
import parsers.fullgc.schema.EnvType;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class EnvParser {

    public static void main(String[] args) throws IOException {

        final String fileName = "env_output.txt";

        final Map<String, Multimap<String, EnvDetail>> programIdEnvDetailMap = LinkedHashMap.newLinkedHashMap(16);

        try (final Stream<String> lines = Files.lines(Path.of("env.txt"))) {

            final UnmodifiableIterator<List<String>> iterator = Iterators.partition(lines.iterator(), 12);

            while (iterator.hasNext()) {
                List<String> env = iterator.next();
                if (env.size() != 12) {
                    System.out.println("Skipping invalid env: " + env);
                    continue;
                }
                EnvDetail envDetail = new EnvDetail(env.get(0), env.get(1), env.get(2), env.get(3), env.get(4),
                        env.get(9), env.get(10));

                final String endId = envDetail.envId();
                if (Strings.isNullOrEmpty(endId)) {
                    System.out.println("Skipping invalid env id: " + endId);
                    continue;
                }
                Matcher matcher = ReadUtils.ENV_PATTERN.matcher(endId);
                if (!matcher.matches()) {
                    System.out.println("Skipping invalid env id: " + endId);
                    continue;
                }
                final String programId = matcher.group(1);
                final String type = envDetail.type();
                if (Strings.isNullOrEmpty(type)) {
                    System.out.println("Skipping invalid env type: " + type);
                    continue;
                }
                programIdEnvDetailMap.computeIfAbsent(programId, k -> ArrayListMultimap.create()).put(type, envDetail);
            }
        }

        try(FileWriter fileWriter = new FileWriter(fileName)) {
            programIdEnvDetailMap.forEach((key, value) -> {
                try {
                    fileWriter.write("PROGRAM ID : " + key);
                    fileWriter.write("\n");
                    fileWriter.write("\n");
                    value.entries().stream().filter(e -> ReadUtils.ENV_TYPE.contains(EnvType.fromString(e.getKey()))).forEach(e -> {
                        try {
                            fileWriter.write(e.getValue().toString());
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
    }
}
