import java.io.IOException;

public interface ContentOutput {
    void write(String content) throws IOException;
}
