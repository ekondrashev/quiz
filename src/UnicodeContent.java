import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UnicodeContent implements Content {
    private final File file;

    public UnicodeContent(final File file) {
        this.file= file;
    }

    @Override
    public String toString() {
        try {
            return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return toString();
    }

    @Override
    public void save(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes("UTF-8"));

    }
}
