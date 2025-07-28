package dsgc;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import dsgc.schema.DsgcEnv;
import dsgc.schema.DsgcSize;
import dsgc.schema.DsgcSweep;
import org.apache.commons.collections4.SetUtils;
import parsers.ReadUtils;
import parsers.schema.EnvDetail;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class AddEnvType {

    public static void main(String[] args) throws IOException {

        final Map<String, DsgcEnv> dsgcEnv = LinkedHashMap.newLinkedHashMap(12000);

        try (CSVReader reader = new CSVReader(new FileReader("DSGC_Merged.csv"))) {
            // Read header
            String[] headers = reader.readNext();
            System.out.println(Arrays.toString(headers));

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Process the data...
                if (Objects.isNull(line[0]) || Objects.isNull(line[2])) {
                    continue;
                }
                // cluster,namespace,aem_service,blobs,blobs_size_gb,candidates,candidates_size_gb,references,duration_hours,mark_references,mark_size_gb
                dsgcEnv.putIfAbsent(line[2], new DsgcEnv(line[0], line[1], line[2], 0L, ReadUtils.parseLong(line[3]), ReadUtils.parseDouble(line[4]),
                        ReadUtils.parseLong(line[5]), ReadUtils.parseDouble(line[6]), ReadUtils.parseLong(line[7]),
                        ReadUtils.parseDouble(line[8]), ReadUtils.parseLong(line[9]),
                        ReadUtils.parseDouble(line[10])));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        System.out.println(dsgcEnv.size());

        final Map<String, EnvDetail> allAemServiceMap = ReadUtils.getEnvDetailSet("all_envs_1.txt");

        final Map<String, EnvDetail> allAemServiceMap2 = ReadUtils.getEnvDetailSet("all_envs_2.txt");

        final Map<String, EnvDetail> envDetailMap = new LinkedHashMap<>(allAemServiceMap);
        allAemServiceMap2.forEach(envDetailMap::putIfAbsent);

        writeDsgcEnvToCsv(dsgcEnv.values(), "DSGC_Merged_WithType.csv", envDetailMap);
    }

    public static void writeDsgcEnvToCsv(Collection<DsgcEnv> envs, String filePath, final Map<String, EnvDetail> envDetailMap) throws IOException {
        // Define headers with sort keys first
        String[] headers = {
                "cluster","namespace","aem_service","blobs","blobs_size_gb","candidates","candidates_size_gb","references","duration_hours","mark_references","mark_size_gb","env_type"
        };

        // Convert data to String arrays with the same column order
        List<String[]> data = envs.stream()
                .map(env -> new String[] {
                        env.cluster(),
                        env.namespace(),
                        env.aemService(),
                        String.valueOf(env.blobs()),
                        String.valueOf(env.blobSize()),
                        String.valueOf(env.candidates()),
                        String.valueOf(env.candidatesSize()),
                        String.valueOf(env.references()),
                        String.valueOf(env.duration()),
                        String.valueOf(env.markReferences()),
                        String.valueOf(env.markSize()),
                        String.valueOf(envDetailMap.containsKey(env.aemService()) ? envDetailMap.get(env.aemService()).type() : ""),
                })
                .toList();

        // Write to CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(headers);
            writer.writeAll(data);
        }
    }
}
