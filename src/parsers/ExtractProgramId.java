package parsers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ExtractProgramId {
    public static void main(String[] args) {
        final String fileName = "backup_disable_env_output.txt";

        final Set<String> programIdSet = LinkedHashSet.newLinkedHashSet(100);

        try (final Stream<String> lines = Files.lines(Path.of("disable_env_output.txt"))) {
            lines.filter(l -> l.startsWith("PROGRAM")).
                    map(l -> l.split(":"))
                    .filter(lArr -> lArr.length == 2).map(lArr -> lArr[1].trim())
                    .filter(ReadUtils::isInteger)
                    .forEach(programIdSet::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(programIdSet.size());
        System.out.println(programIdSet);

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            programIdSet.forEach(key -> {
                try {
                    fileWriter.write(key);
                    fileWriter.write("\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
