import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class Parser {

  private File file;

  public synchronized void setFile(File f) {
    file = f;
  }

  public synchronized File getFile() {
    return file;
  }

  public synchronized String getContent() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      int data;
      while ((data = inputStream.read()) > 0) {
        output += (char) data;
      }
    }
    return output;
  }

  public synchronized String getContentWithoutUnicode() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      int data;
      while ((data = inputStream.read()) > 0) {
        if (data < 0x80) {
          output += (char) data;
        }
      }
    }
    return output;
  }

  public synchronized void saveContent(String content) throws IOException {
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      for (int i = 0; i < content.length(); i += 1) {
        outputStream.write(content.charAt(i));
      }
    }
  }
}
