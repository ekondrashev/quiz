package src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class is thread safe.
 */
public class Parser implements TextFile {
    private final File file;

    public Parser(final File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    @Override
    public synchronized String readContent() throws IOException {
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
    public synchronized void saveContent(String content) throws IOException {
        try (FileOutputStream o = new FileOutputStream(file)) {
            o.write(content.getBytes());
        }
    }
}
