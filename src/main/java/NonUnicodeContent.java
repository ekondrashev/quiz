import java.io.IOException;

/**
 * @{code Content}, providing read operation without Unicode and write operation
 */

public class NonUnicodeContent implements Content {
    private final Content content;

    /**
     * Ctor.
     * @param c  @{code Content} which you want to read in non-Unicode characters
     */
    public NonUnicodeContent(Content c) {
        this.content = c;
    }

    @Override
    public String read() throws IOException {
        return this.content.read().replaceAll("[^\\x00-\\x7F]", "");
    }

    @Override
    public void write(String content) throws IOException {
        this.content.write(content);
    }
}
