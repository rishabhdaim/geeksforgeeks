package parsers.dsgc;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import parsers.dsgc.schema.DsgcEnv;
import parsers.ReadUtils;
import parsers.fullgc.schema.EnvDetail;
import parsers.fullgc.schema.EnvType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AddEnvType {

    public static void main(String[] args) throws IOException, CsvValidationException {

        final Map<String, DsgcEnv> dsgcEnv = LinkedHashMap.newLinkedHashMap(12000);

        final String fileName = "DSGC_Merged.csv";

        ReadUtils.readDsgcEnvs(dsgcEnv, fileName);

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
                        String.valueOf(envDetailMap.containsKey(env.aemService()) ? envDetailMap.get(env.aemService()).type() : EnvType.UNKNOWN),
                })
                .toList();

        // Write to CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(headers);
            writer.writeAll(data);
        }
    }
}
