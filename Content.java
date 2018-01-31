import java.io.IOException;

/**
 * This class is thread safe.
 */
public interface Content {
    String getContent() throws IOException;

    public void saveContent(String content) throws IOException;
}