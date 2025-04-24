package parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import parsers.schema.Count;
import parsers.schema.Size;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SizeCountCalculator {
    public static void main(String[] args) throws IOException {


        Set<String> aemService = ReadUtils.getAemServiceSet(
                "CM_PROD_GRP_1_DEV.txt",
                "CM_PROD_GRP_1_PROD.txt",
                "CM_PROD_GRP_1_STAGE.txt",
                "CM_PROD_GRP_2_DEV.txt",
                "CM_PROD_GRP_2_PROD.txt",
                "CM_PROD_GRP_2_STAGE.txt",
                "CM_PROD_GRP_30.txt");

        Set<String> bigEnvs = ReadUtils.getAemServiceSet(
                "disable_env_output.txt");

        aemService.removeAll(bigEnvs);

        System.out.println(aemService.size());

        try (InputStream sizeStream = Files.newInputStream(Path.of("size.json")); InputStream countStream = Files.newInputStream(Path.of("nodesCount.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Size size = objectMapper.readValue(sizeStream, Size.class);
            Count count = objectMapper.readValue(countStream, Count.class);

            // Process the results
            final Map<String, Double> collectSize = size.data().result().stream()
                    .collect( Collectors.toMap(
                                    result -> result.metric().aemService(),
                                    result -> Math.ceil((Double.parseDouble(result.value()[1].toString())/(1024 * 1024 * 1024)) * 100)/100));
            // Use the data
            System.out.println("Size: " + collectSize.get("cm-p49652-e267736"));
            System.out.println("Total Size: " + collectSize.entrySet().stream()
                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum());

            final Map<String, Long> countSize = count.data().result().stream()
                    .collect( Collectors.toMap(
                            result -> result.metric().aemService(),
                            result -> Math.round(Double.parseDouble(result.value()[1].toString()))));
            // Use the data
            System.out.println("Count: " + countSize.get("cm-p49652-e267736"));
            System.out.println("Total Count: " + countSize.entrySet().stream()
                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToLong(Map.Entry::getValue)
                    .sum());

        }
    }
}
