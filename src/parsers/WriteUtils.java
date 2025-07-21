package parsers;

import com.google.common.collect.ImmutableMap;
import parsers.schema.EnvDetail;
import parsers.schema.EnvType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WriteUtils {

    private WriteUtils() {
        // Utility class, no instantiation
    }

    public static final String ENABLE_ENVS = "enable_env_output.txt";
    public static final String DISABLE_ENVS = "disable_env_output.txt";

    public static void writeEnvsToFile(String enableEnvs, String disableEnvs, Map<String, List<EnvDetail>> programIdEnvDetailMap, Set<String> programIdSet, Map<String, Double> sizeByServiceSmall, Map<String, Set<EnvDetail>> smallEnvs, Map<String, Double> sizeByServiceBig, Map<String, Set<EnvDetail>> bigEnvs, Set<String> skipList) throws IOException {
        try (FileWriter enableEnvWriter = new FileWriter(enableEnvs); FileWriter disableEnvWriter = new FileWriter(disableEnvs)) {
            programIdEnvDetailMap.forEach((key, value) -> {
                if (!programIdSet.contains(key)) {
                    return;
                }

                if (skipList.contains(key)) {
                    return;
                }
                // Single traversal of environments
                for (EnvDetail env : value) {
                    EnvType envType = EnvType.fromString(env.type());
                    if (!ReadUtils.ENUM_SET.contains(envType)) {
                        continue;
                    }


                    if (sizeByServiceSmall.containsKey(env.envId())) {
                        smallEnvs.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(env);
                    }

                    if (sizeByServiceBig.containsKey(env.envId())) {
                        bigEnvs.computeIfAbsent(key, k -> new LinkedHashSet<>()).add(env);
                    }
                }
            });
            System.out.println("Big Envs: " + bigEnvs.size());
            System.out.println("Small Envs: " + smallEnvs.size());
            writeEnvs(enableEnvWriter, smallEnvs, sizeByServiceSmall);
            writeEnvs(disableEnvWriter, bigEnvs, new ImmutableMap.Builder<String, Double>()
                    .putAll(sizeByServiceSmall)
                    .putAll(sizeByServiceBig)
                    .build());
        }
    }

    private static void writeEnvs(final FileWriter fileWriter, final Map<String, Set<EnvDetail>> envDetailMap, Map<String, Double> sizeByService) throws IOException {

        fileWriter.write("\n");
        envDetailMap.forEach((key, value) -> {
            try {
                fileWriter.write("PROGRAM ID : " + key);
                fileWriter.write("\n");
                fileWriter.write("\n");
                value.forEach(e -> {
                    try {
                        fileWriter.write(e.toString() + " " + Math.ceil((sizeByService.get(e.envId()) / (1024 * 1024 * 1024) * 100)) / 100);
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
