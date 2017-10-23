import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

class Reader {

  public String read(InputStream inputStream) throws IOException {
    return read(inputStream, null);
  }

  public String read(InputStream inputStream, Predicate<Integer> predicate) throws IOException {
    StringBuilder sb = new StringBuilder();
    int data;
    while ((data = inputStream.read()) > 0) {
      if (predicate == null || predicate.test(data)) {
        sb.append((char) data);
      }
    }
    return sb.toString();
  }
}
