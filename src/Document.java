package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public interface Document {
    CharSequence read() throws IOException;

    void save(CharSequence content) throws IOException;
}
