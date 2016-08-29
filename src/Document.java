package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public final class Document implements Parsable {
    private File file;
    private String content;

    public Document(final File file) {
        this.file = file;
        content="";
    }

    @Override
    public String parse() throws IOException {
        if (content.isEmpty()) {
            String strings = "";
            try (FileInputStream i = new FileInputStream(file)) {
                int data;
                while ((data = i.read()) > 0) {
                    strings += (char) data;
                }
            }
            content = strings;
        }
        return content;
    }
}
