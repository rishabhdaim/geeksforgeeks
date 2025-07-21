package parsers;

import org.apache.commons.collections4.SetUtils;
import parsers.schema.EnvDetail;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static parsers.ReadUtils.getAllAemServiceSet;

public class NonROProgramId {
    public static void main(String[] args) throws IOException {

        Map<String, Double> sizeByServiceSmall = new LinkedHashMap<>();
        Map<String, Double> sizeByServiceBig = new LinkedHashMap<>();

        Set<String> allAemServiceSet = getAllAemServiceSet();

        Set<String> roAemServiceSet = ReadUtils.getAemServiceSet(
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

        Set<String> nonROProgramIdSet = SetUtils.difference(allAemServiceSet, roAemServiceSet).stream().map(aemService -> {
            Matcher matcher = ReadUtils.ENV_PATTERN.matcher(aemService);
            matcher.matches(); // safe to execute
            return matcher.group(1);
        }).collect(Collectors.toCollection(LinkedHashSet::new));

        Map<String, List<EnvDetail>> programIdEnvDetailMap = ReadUtils.getProgramIdEnvDetailMap();

        ReadUtils.filterBySize(sizeByServiceSmall, ReadUtils.lowerThan200, sizeByServiceBig, ReadUtils.greaterThan200);
//        System.out.println("Small Size: " + sizeByServiceSmall.size());
//        System.out.println("Big Size: " + sizeByServiceBig.size());

//        System.out.println("Missing Program Data : " + SetUtils.difference(nonROProgramIdSet, programIdEnvDetailMap.keySet()));

        final Map<String, Set<EnvDetail>> bigEnvs = LinkedHashMap.newLinkedHashMap(100);

        ReadUtils.combineBigEnvs(bigEnvs);

        final Map<String, Set<EnvDetail>> smallEnvs = LinkedHashMap.newLinkedHashMap(500);

        WriteUtils.writeEnvsToFile(WriteUtils.ENABLE_ENVS, WriteUtils.DISABLE_ENVS, programIdEnvDetailMap, nonROProgramIdSet, sizeByServiceSmall, smallEnvs, sizeByServiceBig, bigEnvs, roAemServiceSet);
    }
}
