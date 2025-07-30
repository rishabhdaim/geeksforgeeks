package parsers.fullgc;

import parsers.ReadUtils;
import parsers.WriteUtils;
import parsers.fullgc.schema.EnvDetail;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProgramIdParser {

    public static void main(String[] args) throws IOException {


        Map<String, Double> sizeByServiceSmall = new LinkedHashMap<>();
        Map<String, Double> sizeByServiceBig = new LinkedHashMap<>();

        Map<String, List<EnvDetail>> programIdEnvDetailMap = ReadUtils.getProgramIdEnvDetailMap();

        Set<String> programIdSet = ReadUtils.getProgramIdSet("programId.txt");

        ReadUtils.filterBySize(sizeByServiceSmall, ReadUtils.lowerThan70, sizeByServiceBig, ReadUtils.greaterThan70);

//        System.out.println("Small Size: " + sizeByServiceSmall.size());
//        System.out.println("Big Size: " + sizeByServiceBig.size());

//        System.out.println("Missing Program Data : " + SetUtils.difference(programIdSet, programIdEnvDetailMap.keySet()));

        final Map<String, Set<EnvDetail>> bigEnvs = LinkedHashMap.newLinkedHashMap(100);

        ReadUtils.combineBigEnvs(bigEnvs);

        final Map<String, Set<EnvDetail>> smallEnvs = LinkedHashMap.newLinkedHashMap(500);

        WriteUtils.writeEnvsToFile(WriteUtils.ENABLE_ENVS, WriteUtils.DISABLE_ENVS, programIdEnvDetailMap, programIdSet, sizeByServiceSmall, smallEnvs, sizeByServiceBig, bigEnvs, Set.of());
    }
}
