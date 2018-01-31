import java.io.*;

public class FileDataSource extends ContentDecorator {

    public FileDataSource(File file, Content wrapper) {
        super(file, wrapper);
    }


    @Override
    public String getContent() throws IOException {
        FileInputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }
        return output;
    }
}
