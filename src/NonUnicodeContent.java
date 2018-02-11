import java.io.IOException;


public class NonUnicodeContent implements Content {
    private Content content;

    public NonUnicodeContent(Content c) {
        this.content = c;
    }

    @Override
    public String toString() {
        return content.toString().replaceAll("[^\\x00-\\x7F]", "");
    }

    @Override
    public void saveContent(String content) throws IOException {
        this.content.saveContent(content);

    }
}
