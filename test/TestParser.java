import java.io.*;

import org.junit.*;
import org.junit.rules.TemporaryFolder;


public class TestParser {
    private static final String UNICODE_TEXT = "ЙЭЫtest";
    private static final String NON_UNICODE_TEXT = "test";

    private static File file;

    @Rule
    public TemporaryFolder tmp = new TemporaryFolder();

    @Before
    public void setFile() throws IOException {
        file = tmp.newFile("test.txt");
    }

    private void setFileContent(File file, String content) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
    }

    @Test
    public void testGetContent() throws IOException {
        setFileContent(file, NON_UNICODE_TEXT);

        Parser parser = new Parser();
        parser.setFile(file);
        Assert.assertEquals(NON_UNICODE_TEXT, parser.getContent());
    }


    @Test
    public void testGetContentWithoutUnicode() throws IOException {
        setFileContent(file, UNICODE_TEXT);
        Parser parser = new Parser();
        parser.setFile(file);
        Assert.assertEquals(NON_UNICODE_TEXT, parser.getContentWithoutUnicode());
    }

    @Test
    public void testSaveContent() throws IOException {
        setFileContent(file, UNICODE_TEXT);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.saveContent(NON_UNICODE_TEXT);
        Assert.assertEquals(NON_UNICODE_TEXT, parser.getContent());
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
        setFileContent(file, NON_UNICODE_TEXT);
        file.setWritable(false);

        Parser parser = new Parser();
        parser.setFile(file);
        parser.saveContent(UNICODE_TEXT);
    }

    @Test(expected = IOException.class)
    public void testGetContentException() throws IOException {
        setFileContent(file, NON_UNICODE_TEXT);
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