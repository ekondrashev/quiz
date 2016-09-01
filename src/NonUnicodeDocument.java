package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public final class NonUnicodeDocument implements Parsable {
    private Parsable parsable;
    private CharSequence content;

    public NonUnicodeDocument(Parsable parsable) {
        this.parsable = parsable;
        content = "";
    }

    @Override
    public CharSequence read() throws IOException {
        CharSequence sequence = parsable.read();
        StringBuilder contentWithoutUnicode = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char symbol = sequence.charAt(i);
            if (symbol < 0x80) {
                contentWithoutUnicode.append(symbol);
            }
        }
        content = contentWithoutUnicode.toString();
        return content;
    }
}
