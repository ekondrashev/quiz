package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public final class UsualStrings implements ParsableStrings {
    private File file;
    private String content;

    public UsualStrings(final File file) {
        this.file = file;
        content="";
    }

    @Override
    public String showStrings() throws IOException {
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
