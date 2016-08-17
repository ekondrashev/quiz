import java.io.IOException;

/**
 * Created by shneo_000 on 15.08.2016.
 */
public interface Parsable {
    String getContent() throws IOException;
    void saveContent(String content) throws IOException;
}
