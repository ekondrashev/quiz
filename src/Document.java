package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public final class Document implements Readable {
    private File file;
    private CharSequence content;

    public Document(final File file) {
        this.file = file;
        content = "";
    }

    @Override
    public CharSequence read() throws IOException {
        synchronized (file) {
            StringBuilder string = new StringBuilder("");
            try (FileInputStream fis = new FileInputStream(file)) {
                int data;
                while ((data = fis.read()) > 0) {
                    string.append((char) data);
                }
            }
            content = string.toString();
            return content;
        }
    }

    @Override
    public void save(CharSequence content) throws IOException {
        synchronized (file) {
            if (this.content.equals(content)) {
                return;
            }
            FileOutputStream fos = new FileOutputStream(file);
            for (int i = 0; i < content.length(); i += 1) {
                fos.write(content.charAt(i));
            }
        }
    }
}
