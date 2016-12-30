import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

/**
 * Test for class NonUnicodeContent
 */
@RunWith(JUnit4.class)
public class NonUnicodeContentTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    private static final String FILE_NAME = "NonUnicodeContentTest.txt";
    private String contentWithUnicode = "Ùcontent";
    private String contentWithoutUnicode = "content";

    /**
     * Test for reading non-Unicode content.
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void NonUnicodeContentTest() throws IOException {
        File file= temp.newFile(FILE_NAME);
        Content content = new NonUnicodeContent(new FileContent(file,"UTF-8"));
        content.write(contentWithUnicode);
        String actual = content.read();
        Assert.assertEquals(contentWithoutUnicode,actual);
    }


}
