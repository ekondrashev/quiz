import java.io.*;
import java.nio.file.*;

/**
 * This class is thread safe.
 */
public class Parser {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent() throws IOException {
        byte[] output = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        return new String(output);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent().replaceAll("[^\\x00-\\x7F]", "");
    }

    public void saveContent(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes());
    }
}
