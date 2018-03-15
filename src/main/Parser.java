import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Parser {

  private AbstractContentReader input;
  private AbstractContentWriter output;

  public Parser(AbstractContentReader input, AbstractContentWriter output) {
      this.input = input;
      this.output = output;
  }

  public synchronized String content() throws IOException {
    return input.content();
  }

  public synchronized void save(String content) throws IOException {
    output.save(content);
  }

}
