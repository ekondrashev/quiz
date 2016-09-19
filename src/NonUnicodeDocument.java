package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public final class NonUnicodeDocument implements Document {
    private Document document;

    public NonUnicodeDocument(Document document) {
        this.document = document;
    }

    @Override
    public CharSequence read() throws IOException {
        CharSequence sequence = document.read();
        StringBuilder contentWithoutUnicode = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char symbol = sequence.charAt(i);
            if (symbol < 0x80) {
                contentWithoutUnicode.append(symbol);
            }
        }
        return contentWithoutUnicode.toString();
    }

    @Override
    public void write(CharSequence content) throws IOException {
        document.write(content);
    }
}
