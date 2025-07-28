package dsgc;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import dsgc.schema.DsgcEnv;
import dsgc.schema.DsgcSize;
import dsgc.schema.DsgcSweep;
import parsers.ReadUtils;
import parsers.schema.EnvDetail;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Difference {

    public static void main(String[] args) throws IOException {

        final Map<String, EnvDetail> allAemServiceMap = ReadUtils.getEnvDetailSet("all_envs_1.txt");

        final Map<String, EnvDetail> allAemServiceMap2 = ReadUtils.getEnvDetailSet("all_envs_2.txt");

        final Map<String, DsgcSize> dsgcSize = LinkedHashMap.newLinkedHashMap(12000);
        final Map<String, DsgcSweep> dsgcSweep = LinkedHashMap.newLinkedHashMap(12000);
        final Map<String, DsgcEnv> dsgcEnv = LinkedHashMap.newLinkedHashMap(12000);
        final Map<String, EnvDetail> envDetailMap = new LinkedHashMap<>(allAemServiceMap);
        allAemServiceMap2.forEach(envDetailMap::putIfAbsent);

        try (CSVReader reader = new CSVReader(new FileReader("DSGC_Size.csv"))) {
            // Read header
            String[] headers = reader.readNext();
            System.out.println(Arrays.toString(headers));

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Process the data...
                if (Objects.isNull(line[0]) || Objects.isNull(line[2])) {
                    continue;
                }
                dsgcSize.putIfAbsent(line[2], new DsgcSize(line[0], line[1], line[2], ReadUtils.parseLong(line[3]), ReadUtils.parseDouble(line[4])));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        try (CSVReader reader = new CSVReader(new FileReader("DSGC_Sweep.csv"))) {
            // Read header
            String[] headers = reader.readNext();
            System.out.println(Arrays.toString(headers));

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Process the data...
                if (Objects.isNull(line[0]) || Objects.isNull(line[2])) {
                    continue;
                }
                dsgcSweep.putIfAbsent(line[2], new DsgcSweep(line[0], line[1], line[2], ReadUtils.parseLong(line[3]), ReadUtils.parseDouble(line[4]),
                        ReadUtils.parseLong(line[5]), ReadUtils.parseDouble(line[6]), ReadUtils.parseLong(line[7]),
                        ReadUtils.parseDouble(line[8])));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        System.out.println("DSGC Size: " + dsgcSize.size());
        System.out.println("DSGC Sweep: " + dsgcSweep.size());
        int envMiss = 0;
        int sweepMiss = 0;

        for (Map.Entry<String, DsgcSize> dsgcSizeEntry : dsgcSize.entrySet()) {
            if (!envDetailMap.containsKey(dsgcSizeEntry.getKey())) {
                System.out.println("Missing Env Detail for DSGC Size: " + dsgcSizeEntry.getKey());
                envMiss++;
                continue;
            }

            if (!dsgcSweep.containsKey(dsgcSizeEntry.getKey())) {
                System.out.println("Missing DSGC Sweep for DSGC Size: " + dsgcSizeEntry.getKey());
                sweepMiss++;
                continue;
            }
            dsgcEnv.putIfAbsent(dsgcSizeEntry.getKey(), new DsgcEnv(dsgcSizeEntry.getValue(), dsgcSweep.get(dsgcSizeEntry.getKey()), 0L, 0.0d));
        }

        System.out.println("DSGC Env: " + dsgcEnv.size());
        System.out.println("Missing Env Detail: " + envMiss);
        System.out.println("Missing DSGC Sweep: " + sweepMiss);

        List<DsgcEnv> sortedEnvs = dsgcEnv.values().stream()
                .sorted(Comparator.comparing(DsgcEnv::candidates)
                        .thenComparing(DsgcEnv::blobs)
                        .thenComparing(DsgcEnv::referencesTotal))
                .toList();

        System.out.println("Sorted Envs: " + sortedEnvs.size());

        try(final FileWriter fw = new FileWriter("DSGC_Envs.txt")) {
            sortedEnvs.forEach(env -> {
                try {
                    fw.write(env.toString());
                    fw.write("\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        writeDsgcEnvToCsv(sortedEnvs, "DSGC_Envs.csv");
    }

    public static void writeDsgcEnvToCsv(Collection<DsgcEnv> envs, String filePath) throws IOException {
        // Define headers with sort keys first
        String[] headers = {
                "Cluster","Namespace","AemService","Candidates","Blobs","ReferencesTotal","References"
        };

        // Convert data to String arrays with the same column order
        List<String[]> data = envs.stream()
                .map(env -> new String[] {
                        env.cluster(),
                        env.namespace(),
                        env.aemService(),
                        String.valueOf(env.candidates()),
                        String.valueOf(env.blobs()),
                        String.valueOf(env.referencesTotal()),
                        String.valueOf(env.references()),
                })
                .toList();

        // Write to CSV file
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(headers);
            writer.writeAll(data);
        }
    }
}
