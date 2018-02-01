import java.io.IOException;

/**
 * This class is thread safe.
 */
public interface Content {
    String getContent() throws IOException;

    void saveContent(String content) throws IOException;

}