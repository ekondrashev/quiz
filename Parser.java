/**
 * Created by 4oc3p on 23.10.2017.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class Parser {

    private static final int UNICODE_START = 0x80;

    private File file;

    public Parser() {
    }

    public Parser(File file) {
        this.file = file;
    }

    public synchronized void setFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        return parseContent(Character.MAX_VALUE);
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        return parseContent(UNICODE_START);
    }

    private String parseContent(int symbolCap) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StringBuilder output = new StringBuilder();
        int data;
        while ((data = inputStream.read()) > 0) {
            if (data < symbolCap) {
                output.append((char) data);
            }
        }
        inputStream.close();
        return output.toString();
    }

    public void saveContent(String content) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(content.getBytes());
        outputStream.close();
    }
}
