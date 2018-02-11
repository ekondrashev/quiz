import java.io.IOException;


public interface Content {
    String toString();
    void save(String content) throws IOException;
}