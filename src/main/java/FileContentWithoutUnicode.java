import java.io.IOException;

public class FileContentWithoutUnicode implements Content {
    private final Content content;

    public FileContentWithoutUnicode(Content c) {
        this.content = c;
    }

    public String read() throws IOException {
        return this.content.read().replaceAll("[^\\x00-\\x7F]", "");
    }

    public void save(String content) throws IOException {
        this.content.save(content);
    }
}
