package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public class ParserWithoutUnicode implements TextFile {
    private final TextFile textFile;

    public ParserWithoutUnicode(TextFile textFile) {
        this.textFile = textFile;
    }

    @Override
    public String readContent() throws IOException {
        String content = textFile.readContent();
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
    public void saveContent(String content) throws IOException {
        textFile.saveContent(content);
    }
}
