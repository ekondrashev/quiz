package decorator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import model.ParsedInterface;

public class UnicodeParsed implements ParsedInterface {

  private final ParsedInterface origin;

  UnicodeParsed(ParsedInterface parser) {

    this.origin = parser;
  }

  @Override
  public File giveFile() {
    return this.origin.giveFile();
  }

  @Override
  public String giveContent() throws IOException {
    FileInputStream input = new FileInputStream(giveFile());
    String output = "";
    int data;
    while ((data = input.read()) > 0) {
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
