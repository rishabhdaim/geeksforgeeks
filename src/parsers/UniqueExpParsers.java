package parsers;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class UniqueExpParsers {

    public static final String DSGC_ROLLOUT_ENVS = "Unique_Expressions_Count.yml";

    static void main() throws CsvValidationException, IOException {

        final String fileName = "Unique_Expressions_Count.csv";
        final Map<String, Integer> uniqueExpCount = new TreeMap<>();

        ReadUtils.readUniqueExp(uniqueExpCount, fileName);

        // ethos50-prod-va7 ns-team-aem-cm-prd-n133111 cm-p138917-e1404442 dev 0.03
        uniqueExpCount.keySet().forEach(System.out::println);
        System.out.println(uniqueExpCount.size());

    }
}
