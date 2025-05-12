package parsers;

import parsers.schema.EnvType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
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
    public static final EnumSet<EnvType> ENUM_SET = EnumSet.of(PROD, DEV, STAGE);

    public static Set<String> getAemServiceSet(String... fileNames) {

        final List<String> lines = readEnvFiles(fileNames);

        return lines.stream().filter(s -> s.startsWith("ethos")).map(s -> s.split(" ")[2]).collect(Collectors.toSet());
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
}
