import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class DocumentString implements Document {
  private java.io.File file;

  public DocumentString(java.io.File file) {
    this.file = file;
  }

  @Override
  public String Read() {
    String output = "";
    int data;
    try (FileInputStream i = new FileInputStream(file)) {
      while ((data = i.read()) > 0) {
        output += (char) data;
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
