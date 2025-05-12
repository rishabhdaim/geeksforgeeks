package parsers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import parsers.schema.EnvDetail;
import parsers.schema.EnvType;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class CmStageParser {

    public static void main(String[] args) throws IOException {

        final Set<EnvDetail> envDetails = HashSet.newHashSet(1800);

        final Map<String, List<EnvDetail>> programIdEnvDetailMap;

        final String fileName = "stage_env_output.txt";


        try (CSVReader reader = new CSVReader(new FileReader("cm_stage.csv"))) {
            // Read header
            String[] headers = reader.readNext();
            System.out.println(Arrays.toString(headers));

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Process the data...
                if (Objects.isNull(line[0]) || Objects.isNull(line[4])) {
                    continue;
                }
                System.out.println(Arrays.toString(line));
                envDetails.add(new EnvDetail(line[3], line[2], line[0], line[1]));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        programIdEnvDetailMap = envDetails.stream().filter(envDetail -> ReadUtils.ENV_PATTERN.matcher(envDetail.envId()).matches()).collect(Collectors.groupingBy(envDetail -> {
            Matcher matcher = ReadUtils.ENV_PATTERN.matcher(envDetail.envId());
            matcher.matches(); // safe to execute
            return matcher.group(1);
        }, LinkedHashMap::new, Collectors.toList()));

        System.out.println(envDetails.size());
        System.out.println(programIdEnvDetailMap);

        try(FileWriter fileWriter = new FileWriter(fileName)) {
            programIdEnvDetailMap.forEach((key, value) -> {
                try {
                    fileWriter.write("PROGRAM ID : " + key);
                    fileWriter.write("\n");
                    fileWriter.write("\n");
                    value.stream().filter(e -> ReadUtils.ENUM_SET.contains(EnvType.fromString(e.type()))).forEach(e -> {
                        try {
                            fileWriter.write(e.toString());
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
