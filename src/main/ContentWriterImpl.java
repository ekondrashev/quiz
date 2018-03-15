import java.io.*;

public class ContentWriterImpl extends AbstractContentWriter {

    public ContentWriterImpl(File file) {
        super(file);
    }

    @Override
    public void save(String content) throws IOException {
        Writer out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        for (int i = 0; i < content.length(); i += 1) {
            out.write(content.charAt(i));
        }

        out.close();
    }
}
