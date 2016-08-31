package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public final class Document implements Parsable {
    private File file;
    private CharSequence content;

    public Document(final File file) {
        this.file = file;
        content = "";
    }

    @Override
    public CharSequence parse() throws IOException {
        StringBuilder string = new StringBuilder("");
        try (FileInputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                string.append((char) data);
            }
        }
        content = string.toString();
        return content;
    }
}
