import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class ReadString implements Parsable {
  private File file;

  public ReadString(File file) {
    this.file = file;
  }

  @Override
  public String getContent() {
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
  public void saveContent(String content) {
    try (FileOutputStream o = new FileOutputStream(file)) {
      for (int i = 0; i < content.length(); i += 1) {
        o.write(content.charAt(i));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
