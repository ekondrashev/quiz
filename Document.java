import java.io.IOException;

/**
 * Created by shneo_000 on 15.08.2016.
 */
public interface Document {
    String Read() throws IOException;
    void Write(String content) throws IOException;
}
