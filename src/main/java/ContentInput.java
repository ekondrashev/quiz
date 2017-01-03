import java.io.IOException;

/**
 * ContentInput interface provides for content (text matter of data source) reading
 */
public interface ContentInput {
    /**
     * Reads content into @{code java.lang.String}
     * @return content into @{code java.lang.String}
     * @throws IOException If an input or output exception occurred
     */
    String read() throws IOException;
}
