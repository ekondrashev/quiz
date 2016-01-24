import java.io.IOException;

/**
 * Created by Igor on 24.01.2016.
 */
public interface Parser {

    String getContent() throws IOException;

    void saveContent(String content) throws IOException;

}
