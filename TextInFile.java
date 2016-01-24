import java.io.*;
import java.nio.file.*;

/**
 * Created by Igor on 24.01.2016.
 */
public class TextInFile implements Parser {

    private File file;

    public TextInFile(final File file) {
        this.file = file;
    }

    @Override
    public String getContent() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), "UTF-8");
    }

    @Override
    public void saveContent(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes());
    }
}
