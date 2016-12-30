import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class for reading and writing content from File
 */
public class FileContent implements Content {

    private final File file;
    private final String charsetName;

    /**
     * Constructor for initializing FileContent object
     * @param file file for reading and writing content
     * @param charsetName charset for reading and writing content from File
     */
    public FileContent(File file, String charsetName) {
        this.file = file;
        this.charsetName = charsetName;
    }

    /**
     * Returning content from file
     * @return Content from file
     * @throws IOException If an input or output exception occurred
     */
    public String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())), charsetName);
    }

    /**
     * Writing content into file
     * @param content content which you want to write into file
     * @throws IOException If an input or output exception occurred
     */
    public void write(String content) throws IOException {
        Files.write(Paths.get(file.getAbsolutePath()), content.getBytes(charsetName));
    }
}
