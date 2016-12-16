import java.io.IOException;

public interface Content {
    String read() throws IOException;
    void save(String content) throws IOException;
}
