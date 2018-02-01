import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSource implements Content {
private final File file;

    public FileDataSource(final File file) {
        this.file= file;
    }

    @Override
    public String getContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));

    }

    @Override
    public void saveContent(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes("UTF-8"));

    }
}
