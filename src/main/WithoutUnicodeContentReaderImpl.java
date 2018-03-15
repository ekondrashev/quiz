import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class WithoutUnicodeContentReaderImpl extends AbstractContentReader {

    public WithoutUnicodeContentReaderImpl(File file) {
        super(file);
    }

    @Override
    public String content() throws IOException {
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
