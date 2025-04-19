package parsers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadUtils {

    private ReadUtils() {
    }

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
