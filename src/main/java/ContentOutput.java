import java.io.IOException;
/**
 * Interface for writing content into source
 */
public interface ContentOutput {
    /**
     * Writing content into source
     * @param content content which you want to write into source
     * @throws IOException If an input or output exception occurred
     */
    void write(String content) throws IOException;
}
