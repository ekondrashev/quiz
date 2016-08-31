package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public interface Parsable {
    CharSequence parse() throws IOException;
   // void saveStrings(String content) throws IOException;
}
