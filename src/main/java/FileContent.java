import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @{code Content}, providing read/write operations to/from @{code java.io.File}
 */
public class FileContent implements Content {

    private final File file;
    private final String charset;

    /**
     * Ctor.
     * @param file @{code java.io.File} for read/write operations
     * @param charset charset for read/write operations to/from @{code java.io.File}
     */
    public FileContent(File file, String charset) {
        this.file = file;
        this.charset = charset;
    }

    @Override
    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), charset);
    }

    @Override
    public void write(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes(charset));
    }
}
