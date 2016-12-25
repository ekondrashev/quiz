import java.io.IOException;

public interface ContentOutput {
    void save(String content) throws IOException;
}
