package src;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public final class NonUnicodeDocument implements Readable {
    private Readable readable;
    private CharSequence content;

    public NonUnicodeDocument(Readable readable) {
        this.readable = readable;
        content = "";
    }

    @Override
    public CharSequence read() throws IOException {
        CharSequence sequence = readable.read();
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

    @Override
    public void save(CharSequence content) throws IOException {
                readable.save(content);
    }
}
