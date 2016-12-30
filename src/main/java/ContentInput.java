import java.io.IOException;

/**
 * Interface for reading content from source
 */
public interface ContentInput {
    /**
     * Returning content from source
     * @return Content from source
     * @throws IOException If an input or output exception occurred
     */
    String read() throws IOException;
}
