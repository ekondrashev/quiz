package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public interface Parser {
    String read() throws IOException;
    void save(String content) throws IOException;
}
