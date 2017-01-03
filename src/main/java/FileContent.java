import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @{code Content}, providing read/write operations to/from @{code java.io.File}
 */
public class FileContent implements Content {

    private final File file;
    private final String charsetName;

    /**
     * Ctor.
     * @param file @{code java.io.File} for read/write operations
     * @param charsetName charset for read/write operations to/from @{code java.io.File}
     */
    public FileContent(File file, String charsetName) {
        this.file = file;
        this.charsetName = charsetName;
    }

    @Override
    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), charsetName);
    }

    @Override
    public void write(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes(charsetName));
    }
}
