import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class ContentReaderImpl extends AbstractContentReader {

    public ContentReaderImpl(File file) {
        super(file);
    }

    @Override
    public String content() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
        String str, output = "";

        while ((str = in.readLine()) != null) {
            output += str;
        }

        in.close();

        return output;
    }

}
