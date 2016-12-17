import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ContentForWorkingWithFile implements Content {
    private final File file;
    public ContentForWorkingWithFile(final File f) {
        this.file = f;
    }

    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), "UTF-8");
    }


    public void save(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes());
    }
}
