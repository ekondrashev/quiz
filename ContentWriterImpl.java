import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ContentWriterImpl extends AbstractContentWriter {

    public ContentWriterImpl(File file) {
        super(file);
    }

    @Override
    public void save(String content) throws IOException {
        FileOutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
