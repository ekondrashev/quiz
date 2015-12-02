import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
import org.junit.rules.TemporaryFolder;


public class TestParser {
    private static final String SIMPLE_TEXT = "simple text";
    private static final String UNICODE_TEXT = "ЙЭЫtest";
    private static final String WITHOUT_UNICODE_TEXT = "test";

    private static File file;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Before
    public void setFile() throws IOException {
        file = tmp.newFile("test.txt");
    }

    private void setFileContent(File file, String content) throws IOException {
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(content);
        }
    }

    @Test
    public void testGetContent() throws IOException {
        setFileContent(file, SIMPLE_TEXT);

        Parser parser = new Parser();
        parser.setFile(file);
        Assert.assertEquals(SIMPLE_TEXT, parser.getContent());
    }


    @Test
    public void testGetContentWithoutUnicode() throws IOException {
        setFileContent(file, UNICODE_TEXT);
        Parser parser = new Parser();
        parser.setFile(file);
        Assert.assertEquals(WITHOUT_UNICODE_TEXT, parser.getContentWithoutUnicode());
    }

    @Test
    public void testSaveContent() throws IOException {
        setFileContent(file, UNICODE_TEXT);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.saveContent(SIMPLE_TEXT);
        Assert.assertEquals(SIMPLE_TEXT, parser.getContent());
    }

    @Test(expected = NullPointerException.class)
    public void testNoFileException() throws IOException {
        file = null;

        Parser parser = new Parser();
        parser.setFile(file);
        parser.getContent();
    }

    @Test(expected = IOException.class)
    public void testSaveContentException() throws IOException {
        setFileContent(file, SIMPLE_TEXT);
        file.setWritable(false);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.saveContent(UNICODE_TEXT);
    }

    @Test(expected = IOException.class)
    public void testGetContentException() throws IOException {
        setFileContent(file, SIMPLE_TEXT);
        file.setReadable(false);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.getContent();
    }

    @Test(expected = IOException.class)
    public void testGetContentWithoutUnicodeException() throws IOException {
        setFileContent(file, UNICODE_TEXT);
        file.setReadable(false);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.getContentWithoutUnicode();
    }
}
