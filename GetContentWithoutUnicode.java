import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class GetContentWithoutUnicode extends ContentDecorator {

    public GetContentWithoutUnicode(File file, Content wrapper) {
        super(file, wrapper);
    }


    @Override
    public String getContent() throws IOException {
        FileInputStream i = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        return output;
    }
}
