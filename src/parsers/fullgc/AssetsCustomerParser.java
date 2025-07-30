package parsers.fullgc;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.commons.collections4.SetUtils;
import parsers.ReadUtils;
import parsers.fullgc.schema.AssetsEnv;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AssetsCustomerParser {
    public static void main(String[] args) throws IOException {

        final Set<AssetsEnv> assetsEnvs = HashSet.newHashSet(1800);

        try (CSVReader reader = new CSVReader(new FileReader("assets-customers.csv"))) {
            // Read header
            String[] headers = reader.readNext();
            System.out.println(Arrays.toString(headers));

            String[] line;
            while ((line = reader.readNext()) != null) {
                // Process the data...
                if (Objects.isNull(line[0]) || Objects.isNull(line[4])) {
                    continue;
                }
                assetsEnvs.add(new AssetsEnv(line[0], line[1], line[2], line[3], line[4], createAemService(line[4], line[0])));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        System.out.println(assetsEnvs.size());
//        System.out.println(assetsEnvs);
        Set<String> assetEnvsSet = assetsEnvs.stream().map(AssetsEnv::aemService).collect(Collectors.toSet());

        Set<String> aemService = ReadUtils.getAemServiceSet(
                        "CM_PROD_GRP_1_DEV.txt",
                        "CM_PROD_GRP_1_PROD.txt",
                        "CM_PROD_GRP_1_STAGE.txt",
                        "CM_PROD_GRP_2_DEV.txt",
                        "CM_PROD_GRP_2_PROD.txt",
                        "CM_PROD_GRP_2_STAGE.txt",
                        "CM_PROD_GRP_30.txt",
                        "CM_PROD_GRP_4.txt",
                        "CM_PROD_GRP_8.txt",
                        "CM_PROD_GRP_40.txt",
                        "CM_PROD_GRP_5_1.txt",
                        "CM_PROD_GRP_5_2.txt",
                        "CM_PROD_GRP_31_1.txt",
                        "CM_PROD_GRP_31_2.txt",
                        "CM_PROD_GRP_32_1.txt",
                        "CM_PROD_GRP_32_2.txt",
                        "CM_PROD_GRP_33_1.txt",
                        "CM_PROD_GRP_33_2.txt");

        System.out.println(aemService.size());

        System.out.println(SetUtils.intersection(assetEnvsSet, aemService).size());
        System.out.println(SetUtils.difference(assetEnvsSet, aemService).size());
    }

    private static String createAemService(String programId, String envId) {
        return "cm-p" + programId + "-e" + envId;
    }
}
