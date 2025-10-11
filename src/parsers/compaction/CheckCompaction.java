package parsers.compaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import parsers.ReadUtils;
import parsers.compaction.schema.CompactResult;
import parsers.fullgc.schema.Count;
import parsers.fullgc.schema.Size;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CheckCompaction {

    static void main() throws IOException {

        Set<String> aemService = new HashSet<>(ReadUtils.readEnvFiles("compaction_sample.txt"));
        Map<String, CompactResult> compactResultMap = new LinkedHashMap<>();

        try (InputStream currentSizeStream = Files.newInputStream(Path.of("storageSize.json")); InputStream originalSizeStream = Files.newInputStream(Path.of("storageSize_original.json"))) {
            ObjectMapper objectMapper = new ObjectMapper();
            Size originalSize = objectMapper.readValue(originalSizeStream, Size.class);
            Size currentSize = objectMapper.readValue(currentSizeStream, Size.class);


            // original size on 5th October
            final Map<String, Double> originalSizeMap = originalSize.data().result().stream()
                    .collect( Collectors.toMap(
                            result -> result.metric().aemService(),
                            result -> Math.ceil((Double.parseDouble(result.value()[1].toString())/(1024 * 1024 * 1024)) * 100)/100));
            // Use the data
            System.out.println("Original Size: " + originalSizeMap.entrySet().stream()
                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum());

            // Current Size
            final Map<String, Double> currentSizeMap = currentSize.data().result().stream()
                    .collect( Collectors.toMap(
                            result -> result.metric().aemService(),
                            result -> Math.ceil((Double.parseDouble(result.value()[1].toString())/(1024 * 1024 * 1024)) * 100)/100));
            // Use the data
            System.out.println("Current Size: " + currentSizeMap.entrySet().stream()
                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum());

            aemService.forEach(e -> compactResultMap.put(e, new CompactResult(e, originalSizeMap.get(e), currentSizeMap.get(e))));
            compactResultMap.values().forEach(System.out::println);
        }
    }
}
