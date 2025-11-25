package parsers.compaction;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.AtomicDouble;
import parsers.ReadUtils;
import parsers.compaction.schema.Env;
import parsers.compaction.schema.CompactResult;
import parsers.fullgc.schema.Size;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class CheckCompaction {

    static void main() throws IOException {

        final String[] fileName = {"CM_Large_Envs.txt"};

        final Map<String, Env> envDetailSet = ReadUtils.getEnvMap(fileName).entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println(envDetailSet.size());
        System.out.println("---------All Envs------------->");
//        envDetailSet.forEach((k, _) -> System.out.println(envDetailSet.get(k)));
        System.out.println("---------All Envs------------->");
        final Set<String> aemService = ReadUtils.getAemServiceSet(fileName);
        System.out.println(aemService.size());
        final Map<String, CompactResult> compactResultMap = new LinkedHashMap<>();

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
//                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum());

            // Current Size
            final Map<String, Double> currentSizeMap = currentSize.data().result().stream()
                    .collect( Collectors.toMap(
                            result -> result.metric().aemService(),
                            result -> Math.ceil((Double.parseDouble(result.value()[1].toString())/(1024 * 1024 * 1024)) * 100)/100));
            // Use the data
            System.out.println("Current Size: " + currentSizeMap.entrySet().stream()
//                    .filter(entry -> aemService.contains(entry.getKey()))
                    .mapToDouble(Map.Entry::getValue)
                    .sum());

            aemService.forEach(e -> compactResultMap.put(e, new CompactResult(e, originalSizeMap.get(e), currentSizeMap.get(e))));
            compactResultMap.values().forEach(System.out::println);

            System.out.println("Needs repeat for following");
            int notFound = 0;
            int needRepeat = 0;
            AtomicInteger twentyFive = new AtomicInteger();
            AtomicInteger fifty = new AtomicInteger();
            AtomicInteger hundred = new AtomicInteger();
            AtomicDouble increase = new AtomicDouble(0.0);

            for (String s : aemService) {
                if (!originalSizeMap.containsKey(s) || !currentSizeMap.containsKey(s)) {
//                    System.err.println("Env not found: " + s);
//                    System.err.println("Env not found: " + envDetailSet.get(s));
                    notFound++;
                    continue;
                }
                if (Double.compare(originalSizeMap.get(s), currentSizeMap.get(s)) <= 0) {
//                    System.out.println(envDetailSet.get(s));
                    needRepeat++;
                }
            }
            System.out.println("Need Repeat : " + needRepeat);
            System.out.println("Not Found : " + notFound);

            currentSizeMap.forEach((k, v) ->  {
                if (originalSizeMap.containsKey(k)) {

                    if (originalSizeMap.get(k) - v >= 25 && originalSizeMap.get(k) - v <= 50) {
                        twentyFive.getAndIncrement();
                    }

                    if (originalSizeMap.get(k) - v >= 50 && originalSizeMap.get(k) - v <= 100) {
                        fifty.getAndIncrement();
                    }
                    if (originalSizeMap.get(k) - v >= 100) {
                        hundred.getAndIncrement();
                    }

                    if (v - originalSizeMap.get(k) >= 0) {
                        increase.getAndAdd(v - originalSizeMap.get(k));
                    }
                }
            });

            System.out.println("TwentyFive : " + twentyFive.get());
            System.out.println("Fifty : " + fifty.get());
            System.out.println("hundred : " + hundred.get());
            System.out.println("increase : " + increase.get());
        }
    }
}
