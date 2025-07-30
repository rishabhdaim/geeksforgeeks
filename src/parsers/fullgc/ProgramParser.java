package parsers.fullgc;

import com.google.common.base.Strings;
import com.google.common.collect.Iterators;
import com.google.common.collect.UnmodifiableIterator;
import parsers.ReadUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.stream.Stream;

public class ProgramParser {

    public static void main(String[] args) throws IOException {

        final String fileName = "programId.txt";

        final Set<String> programIdSet = LinkedHashSet.newLinkedHashSet(100);

        try(final Stream<String> lines = Files.lines(Path.of("programId.txt"))) {
            lines.filter(ReadUtils::isInteger).map(String::trim).forEach(programIdSet::add);
        }

        try (final Stream<String> lines = Files.lines(Path.of("program.txt"))) {

            final UnmodifiableIterator<List<String>> iterator = Iterators.partition(lines.iterator(), 4);

            while (iterator.hasNext()) {
                List<String> program = iterator.next();
                if (program.size() != 4) {
                    System.out.println("Skipping invalid program: " + program);
                    continue;
                }

                final String programId = program.get(0);
                if (Strings.isNullOrEmpty(programId)) {
                    System.out.println("Skipping invalid program id: " + programId);
                    continue;
                }
                Matcher matcher = ReadUtils.PROGRAM_PATTERN.matcher(programId);
                if (!matcher.matches()) {
                    System.out.println("Skipping invalid program id: " + programId);
                    continue;
                }

                programIdSet.add(programId);
            }
        }

        try(FileWriter fileWriter = new FileWriter(fileName)) {
            programIdSet.forEach(key -> {
                try {
                    fileWriter.write(key);
                    fileWriter.write("\n");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }
}
