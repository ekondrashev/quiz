package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public class WithoutUnicodeParser implements Conteiner {
    private final Conteiner conteiner;

    public WithoutUnicodeParser(Conteiner parser) {
        this.conteiner = parser;
    }

    @Override
    public String read() throws IOException {
        String content = conteiner.read();
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
        conteiner.save(content);
    }
}
