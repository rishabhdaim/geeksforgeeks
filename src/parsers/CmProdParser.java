package parsers;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CmProdParser {
    public static void main(String[] args) throws IOException {

        final String enableFileName = "output.yml";
        final List<String> enableLines = ReadUtils.readEnvFiles("enable_env_output.txt");

        writeEnvs(enableLines, enableFileName);

        final String disableFileName = "disable_output.yml";
        final List<String> disbaleLines = ReadUtils.readEnvFiles("disable_env_output.txt");
        writeEnvs(disbaleLines, disableFileName);

    }

    private static void writeEnvs(final List<String> lines, final String fileName) throws IOException {
        final List<ClusterNamespace> clusterNamespaces = new ArrayList<>();

        final Map<String, List<String>> listMap = new Rules(
                lines.stream()
                        .filter(l -> l.startsWith("ethos"))
                        .map(env -> env.split(" "))
                        .collect(Collectors.groupingBy(e -> e[0].trim(),
                                Collectors.mapping(e -> e[1].trim() + "#" + e[2].trim(), Collectors.toCollection(LinkedHashSet::new))))
                        .entrySet().stream()
                        .map(e -> new ClusterNamespace(List.of(e.getKey()), new ArrayList<>(e.getValue())))
                        .toList()
        ).getClusterNamespaceList().stream()
                .collect(Collectors.toMap(c -> c.getCluster().getFirst(), ClusterNamespace::getNamespace, (existing, replacement) -> existing, // Merge function for duplicate keys
                        LinkedHashMap::new));

        listMap.entrySet().stream()
                .flatMap(entry ->
                        entry.getValue().stream()
                                .map(v -> v.split("#"))
                                .collect(Collectors.toMap(e -> e[0], e -> e[1], (a, b) -> a + "#" + b, LinkedHashMap::new))
                                .entrySet().stream()
                                .collect(Collectors
                                        .groupingBy(e -> entry.getKey(), LinkedHashMap::new, Collectors
                                                .mapping(e -> e.getKey() + "#" + e.getValue(), Collectors
                                                        .joining(","))))
                                .entrySet().stream()
                                .map(e -> new ClusterNamespace(List.of(e.getKey()), Arrays
                                        .stream(e.getValue().split(",")).toList())
                                )
                )
                .forEach(clusterNamespaces::add);


        final DumperOptions options = new DumperOptions();
        PrintWriter writer = new PrintWriter(fileName);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(options);
        yaml.dump(new Rules(clusterNamespaces), writer);

        final List<String> stringList = Files.readAllLines(Path.of(fileName)).stream()
                .map(l -> l.replace("#", " #"))
                .toList();
        Files.write(Path.of(fileName), stringList);
    }
}
