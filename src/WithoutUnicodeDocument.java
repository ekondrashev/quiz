package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public class WithoutUnicodeDocument implements Document {
    private final Document document;

    public WithoutUnicodeDocument(Document document) {
        this.document = document;
    }

    @Override
    public String read() throws IOException {
        String content = document.read();
        StringBuilder contentWithoutUnicode = new StringBuilder();
        for (int i = 0; i < content.length(); i++) {
            char symbol = content.charAt(i);
            if (symbol < 0x80) {
                contentWithoutUnicode.append(symbol);
            }
        }
        return contentWithoutUnicode.toString();
    }

    @Override
    public void save(String content) throws IOException {
        document.save(content);
    }
}
