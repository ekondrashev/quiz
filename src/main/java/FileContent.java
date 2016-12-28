import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileContent implements Content {
    private final File file;
    private final String charsetName;

    public FileContent(File file, String charsetName) {
        this.file = file;
        this.charsetName = charsetName;
    }

    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), charsetName);
    }


    public void write(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes(charsetName));
    }
}
