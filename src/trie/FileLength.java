package trie;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileLength {
    static void main() throws IOException {
        File file = new File("assets-customers.csv");
        System.out.println(file.length());
        InputStream is = new FileInputStream(file);
        if (is instanceof FileInputStream) {
            FileInputStream fis = (FileInputStream) is;
            System.out.println(fis.getChannel().size());
        }

        InputStream bais = new ByteArrayInputStream(is.readAllBytes());
        if (bais instanceof ByteArrayInputStream) {
            System.out.println(bais.available());
        }

        System.out.println(bais.markSupported());

        if (bais.markSupported()) {
            bais.mark(Integer.MAX_VALUE);
            // Read some bytes
            System.out.println(IOUtils.consume(bais));
            // Reset back to marked position
            bais.reset();
        }

        File tempFile = File.createTempFile("tempFile", ".txt");
        tempFile.deleteOnExit();
        try (OutputStream out = new FileOutputStream(tempFile)) {
            IOUtils.copy(bais, out); // Copy all bytes to file
        }

        FileInputStream fis = new FileInputStream(tempFile);
        System.out.println(fis.getChannel().size());
        System.out.println(tempFile.exists());
        tempFile.delete();
        System.out.println(tempFile.exists());
    }
}
