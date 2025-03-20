package parsers;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CmProdParser {
    public static void main(String[] args) throws IOException {

        final List<ClusterNamespace> clusterNamespaces = new ArrayList<>();
        final String fileName = "output.yml";

        final List<String> lines = Stream.of(
                        Files.readAllLines(Path.of("CM_PROD_GRP_30.txt")).stream()
                ).flatMap(s -> s)
                .toList();

        Map<String, List<String>> listMap = new Rules(
                lines.stream()
                        .filter(l -> l.startsWith("ethos"))
                        .map(env -> env.split(" "))
                        .collect(Collectors.groupingBy(e -> e[0].trim(),
                                Collectors.mapping(e -> e[1].trim() + "#" + e[2].trim(), Collectors.toSet())))
                        .entrySet().stream()
                        .map(e -> new ClusterNamespace(List.of(e.getKey()), new ArrayList<>(e.getValue())))
                        .toList()
        ).getClusterNamespaceList().stream()
                .collect(Collectors.toMap(c -> c.getCluster().getFirst(), ClusterNamespace::getNamespace));

        listMap.entrySet().stream()
                .flatMap(entry ->
                        entry.getValue().stream()
                                .map(v -> v.split("#"))
                                .collect(Collectors.toMap(e -> e[0], e -> e[1], (a, b) -> a + "#" + b))
                                .entrySet().stream()
                                .collect(Collectors
                                        .groupingBy(e -> entry.getKey(), Collectors
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
