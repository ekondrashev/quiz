import java.io.File;
import java.io.IOException;

/**
 * This class is thread safe.
 */
public class ParserSynch implements Parser {

  private Parser parser;

  public ParserSynch(Parser parser) {
    this.parser = parser;
  }

  @Override
  public synchronized void setFile(File file) {
    parser.setFile(file);
  }

  @Override
  public synchronized File getFile() {
    return parser.getFile();
  }

  @Override
  public synchronized String getContent() throws IOException {
    return parser.getContent();
  }

  @Override
  public synchronized String getContentWithoutUnicode() throws IOException {
    return parser.getContentWithoutUnicode();
  }

  @Override
  public synchronized void saveContent(String content) throws IOException {
    parser.saveContent(content);
  }
}
