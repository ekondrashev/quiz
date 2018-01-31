import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveContent extends ContentDecorator {
    public SaveContent(File file, Content wrapper) {
        super(file, wrapper);
    }
    public void saveContent(String content) throws IOException {
        FileOutputStream o = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            o.write(content.charAt(i));
        }
    }
}
