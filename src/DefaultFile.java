package src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class DefaultFile implements File {
    private final java.io.File file;

    public DefaultFile(final java.io.File file) {
        this.file = file;
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
