package decorator;

import java.io.File;
import java.io.IOException;

import model.ParserInterface;

public class SynchronizedParser implements ParserInterface {

  private final ParserInterface origin;

  SynchronizedParser(ParserInterface parser) {
    this.origin = parser;
  }

  @Override
  public synchronized void setFile(File file) {
    this.origin.setFile(file);
  }

  @Override
  public synchronized File getFile() {
    return this.origin.getFile();
  }

  @Override
  public String getContent() throws IOException {
    return this.origin.getContent();
  }

  @Override
  public void saveContent(String content) throws IOException {
    this.origin.saveContent(content);
  }
}
