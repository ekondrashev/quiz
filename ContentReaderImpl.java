import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ContentReaderImpl extends AbstractContentReader {

    public ContentReaderImpl(File file) {
        super(file);
    }

    @Override
    public String content() throws IOException {
        FileInputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            output += (char) data;
        }
        return output;
    }

}
