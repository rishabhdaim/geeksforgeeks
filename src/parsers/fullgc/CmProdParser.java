package parsers.fullgc;

import parsers.ReadUtils;
import parsers.WriteUtils;

import java.io.IOException;
import java.util.List;

public class CmProdParser {
    public static void main(String[] args) throws IOException {

        final String enableFileName = "output.yml";
        final List<String> enableLines = ReadUtils.readEnvFiles("enable_env_output.txt");

        WriteUtils.writeYaml(enableLines, enableFileName);

        final String disableFileName = "disable_output.yml";
        final List<String> disableLines = ReadUtils.readEnvFiles("disable_env_output.txt");
        WriteUtils.writeYaml(disableLines, disableFileName);
    }
}
