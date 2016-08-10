package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class DefaultParser implements Parser {
    private final File file;

    public DefaultParser(final File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public synchronized String read() throws IOException {
        try (FileInputStream i = new FileInputStream(file)) {
            String output = "";
            int data;
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
            return output;
        }
    }


    @Override
    public synchronized void save(String content) throws IOException {
        try (FileOutputStream o = new FileOutputStream(file)) {
            o.write(content.getBytes());
        }
    }
}
