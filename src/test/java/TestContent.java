import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.IOException;

@RunWith(JUnit4.class)
public class TestContent {
    private String contentWithUnicode = "Ùcontent";
    private String contentWithoutUnicode = "content";
    private File file;

    @Before
    public void init () {
         file = new File("src/main/resources/contentTest.txt");
    }

    @Test
    public void ContentForWorkingWithFileTest() throws IOException {

        FileContent fileContent = new FileContent(file);
        fileContent.save(contentWithUnicode);
        String actual = fileContent.read();
        Assert.assertEquals(contentWithUnicode,actual);
    }

    @Test
    public void ContentForWorkingWithFileWithoutUnicodeTest() throws IOException {

        Content content = new NonUnicodeContent(new FileContent(file));
        content.save(contentWithUnicode);
        String actual = content.read();
        Assert.assertEquals(contentWithoutUnicode,actual);
    }

    @After
    public void after(){
        file.delete();
    }

}
