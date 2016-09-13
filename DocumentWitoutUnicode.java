import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by shneo_000 on 15.08.2016.
 */
public class DocumentWitoutUnicode implements Document {
    private File file;

    public DocumentWitoutUnicode(File file) {
        this.file = file;
    }

    @Override
    public String Read() {
        String output = "";
        int data;
        try (FileInputStream i = new FileInputStream(file)) {
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    @Override
    public void Write(String content) {
        try (FileOutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
