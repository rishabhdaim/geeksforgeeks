package parsers.dsgc;

import com.opencsv.exceptions.CsvValidationException;
import parsers.dsgc.schema.DsgcEnv;
import parsers.ReadUtils;
import parsers.WriteUtils;
import parsers.fullgc.schema.EnvType;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DsgcEnvParser {

    public static final String DSGC_ROLLOUT_ENVS = "dsgc_rollout_envs.yml";

    public static void main(String[] args) throws IOException, CsvValidationException {

        final String fileName = "Rollout_Stage_8.csv";
        final Map<String, DsgcEnv> dsgcEnv = LinkedHashMap.newLinkedHashMap(12000);

        ReadUtils.readDsgcEnvs(dsgcEnv, fileName);

        List<String> dsgcEnvs = dsgcEnv.values().stream().filter(e -> e.candidates() <=1000000000000L && e.envType() == EnvType.DEV).map(DsgcEnv::toString).toList();
        // ethos50-prod-va7 ns-team-aem-cm-prd-n133111 cm-p138917-e1404442 dev 0.03
        dsgcEnvs.forEach(System.out::println);
        System.out.println(dsgcEnvs.size());

        WriteUtils.writeYaml(dsgcEnvs, DSGC_ROLLOUT_ENVS);
    }
}
