package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public final class NonUnicode implements Parsable {
    private Parsable parsable;
    private String content;

    public NonUnicode(Parsable parsable) {
        this.parsable = parsable;
        content = "";
    }

    @Override
    public String parse() throws IOException {
            String strings = parsable.parse();
            StringBuilder contentWithoutUnicode = new StringBuilder();
            for (int i = 0; i < strings.length(); i++) {
                char symbol = strings.charAt(i);
                if (symbol < 0x80) {
                    contentWithoutUnicode.append(symbol);
                }
            }
            content = contentWithoutUnicode.toString();

        return content;
    }
}
