import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class Parser {

  private Reader reader;
  private File file;

  public Parser(Reader reader) {
    this.reader = reader;
  }

  public synchronized void setFile(File f) {
    file = f;
  }

  public synchronized File getFile() {
    return file;
  }

  public synchronized String getContent() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      output = reader.read(inputStream);
    }
    return output;
  }

  public synchronized String getContentWithoutUnicode() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      output = reader.read(inputStream, data -> data < 0x80);
    }
    return output;
  }

  public synchronized void saveContent(String content) throws IOException {
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      byte[] bytes = content.getBytes();
      outputStream.write(bytes, 0, bytes.length);
    }
  }
}
