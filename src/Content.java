import java.io.IOException;


public interface Content {
    String toString();
    void saveContent(String content) throws IOException;

}