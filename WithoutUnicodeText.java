import java.io.IOException;

/**
 * Created by Igor on 24.01.2016.
 */
public class WithoutUnicodeText implements Parser {

    private final Parser parser;

    public WithoutUnicodeText(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String getContent() throws IOException {
        return this.parser.getContent()
                .replaceAll("[^\\x00-\\x7F]", "");
    }

    @Override
    public void saveContent(String content) throws IOException {
        this.parser.saveContent(content);
    }
}
