package src;

import java.io.File;
import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public interface TextFile {
    String readContent() throws IOException;
    void saveContent(String content) throws IOException;
}
