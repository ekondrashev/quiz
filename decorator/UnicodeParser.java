package decorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import model.ParserInterface;

public class UnicodeParser implements ParserInterface {

  private final ParserInterface origin;

  UnicodeParser(ParserInterface parser) {
    this.origin = parser;
  }

  @Override
  public void setFile(File file) {
    this.origin.setFile(file);
  }

  @Override
  public File getFile() {
    return this.origin.getFile();
  }

  @Override
  public String getContent() throws IOException {
    FileInputStream inputStream = new FileInputStream(getFile());
    String output = "";
    int data;
    while ((data = inputStream.read()) > 0) {
      if (data < 0x80) {
        output += (char) data;
      }
    }
    return output;
  }

  @Override
  public void saveContent(String content) throws IOException {
    this.origin.saveContent(content);
  }
}
