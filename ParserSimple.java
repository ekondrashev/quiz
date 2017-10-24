import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParserSimple implements Parser {

  private Reader reader;
  private File file;

  public ParserSimple(Reader reader) {
    this.reader = reader;
  }

  @Override
  public void setFile(File file) {
    this.file = file;
  }

  @Override
  public File getFile() {
    return file;
  }

  @Override
  public String getContent() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      output = reader.read(inputStream);
    }
    return output;
  }

  @Override
  public String getContentWithoutUnicode() throws IOException {
    String output = "";
    try (FileInputStream inputStream = new FileInputStream(file)) {
      output = reader.read(inputStream, data -> data < 0x80);
    }
    return output;
  }

  @Override
  public void saveContent(String content) throws IOException {
    try (FileOutputStream outputStream = new FileOutputStream(file)) {
      byte[] bytes = content.getBytes();
      outputStream.write(bytes, 0, bytes.length);
    }
  }
}
