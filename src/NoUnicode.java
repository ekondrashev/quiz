import java.io.IOException;


public class NoUnicode implements Content {
private Content content;

    public NoUnicode(Content c) {
        this.content = c;
    }

    @Override
    public String getContent() throws IOException {
        return this.content.getContent().replaceAll("[^\\x00-\\x7F]", "");
    }

    @Override
    public void saveContent(String content) throws IOException {
        this.content.saveContent(content);

    }
}
