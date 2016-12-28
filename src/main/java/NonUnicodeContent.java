import java.io.IOException;

public class NonUnicodeContent implements Content {
    private final Content content;

    public NonUnicodeContent(Content c) {
        this.content = c;
    }

    public String read() throws IOException {
        return this.content.read().replaceAll("[^\\x00-\\x7F]", "");
    }

    public void write(String content) throws IOException {
        this.content.write(content);
    }
}
