package decorator;

import java.io.IOException;

import model.ParsedInterface;

public class UnicodeParsed implements ParsedInterface {

  private final ParsedInterface origin;

  UnicodeParsed(ParsedInterface parser) {

    this.origin = parser;
  }

  @Override
  public String content() throws IOException {
    String input = this.origin.content();
    String output = "";
    int data;
    for (int i = 0; i < input.length(); i++) {
      data = input.codePointAt(i);
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
