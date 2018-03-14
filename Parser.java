import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {

  private AbstractContentReader reader;
  private AbstractContentWriter writer;

  public Parser(AbstractContentReader reader, AbstractContentWriter writer) {
      this.reader = reader;
      this.writer = writer;
  }

  public synchronized String content() throws IOException {
    return reader.content();
  }

  public synchronized void save(String content) throws IOException {
    writer.save(content);
  }

}
