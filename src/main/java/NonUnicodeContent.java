import java.io.IOException;

/**
 * Class for reading and writing non-Unicode content
 */

public class NonUnicodeContent implements Content {
    private final Content content;

    /**
     * Constructor for initializing NonUnicodeContent object
     * @param c content which you want to read in non-Unicode characters
     */
    public NonUnicodeContent(Content c) {
        this.content = c;
    }

    /**
     * Returning non-Unicode content from source
     * @return non-Unicode content from source
     * @throws IOException If an input or output exception occurred
     */
    public String read() throws IOException {
        return this.content.read().replaceAll("[^\\x00-\\x7F]", "");
    }

    /**
     * Writing content into source
     * @param content content which you want to write into source
     * @throws IOException If an input or output exception occurred
     */
    public void write(String content) throws IOException {
        this.content.write(content);
    }
}
