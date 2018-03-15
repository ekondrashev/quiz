import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ParserTest {

    private final String testFileName = "testFile.name";
    private final String withUnicodeContent = ";alsndлоішватфЩЦЗЩШУЬЦьдвапжвазщпnmb";
    private final String withoutUnicodeContent = ";alsndnmb";

    @Test
    public void parserTest() {
        File f = new File(testFileName);
        f.deleteOnExit();

        Parser p = new Parser(
                new ContentReaderImpl(f),
                new ContentWriterImpl(f)
        );
        try {
            p.save(withUnicodeContent);

            String actual = p.content();
            Assertions.assertEquals(withUnicodeContent, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void withoutUnicodeParserTest() {
        File f = new File(testFileName);
        f.deleteOnExit();

        Parser p = new Parser(
                new WithoutUnicodeContentReaderImpl(f),
                new ContentWriterImpl(f)
        );
        try {
            p.save(withUnicodeContent);

            String actual = p.content();
            Assertions.assertEquals(withoutUnicodeContent, actual);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
