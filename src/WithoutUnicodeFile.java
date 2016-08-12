package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public class WithoutUnicodeFile implements File {
    private final File fileContent;

    public WithoutUnicodeFile(File fileContent) {
        this.fileContent = fileContent;
    }

    @Override
    public String read() throws IOException {
        String content = fileContent.read();
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
        fileContent.save(content);
    }
}
