import org.junit.*;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

/**
 * Test for class FileContent
 */
@RunWith(JUnit4.class)
public class FileContentTest {


    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    private static final String FILE_NAME = "fileContentTest.txt";
    private String contentWithUnicode = "Ùcontent";

    /**
     * Test  for methods - read and write.
     * @throws IOException If an input or output exception occurred
     */
    @Test
    public void fileContentTest() throws IOException {
        File file= temp.newFile(FILE_NAME);

        FileContent fileContent = new FileContent(file,"UTF-8");
        fileContent.write(contentWithUnicode);
        String actual = fileContent.read();
        Assert.assertEquals(contentWithUnicode,actual);
    }


}
