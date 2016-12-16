import junit.framework.Assert;
import org.junit.After;
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
         file = new File("tests/contentTest.txt");
    }

    @Test
    public void ContentForWorkingWithFileTest() throws IOException {

        ContentForWorkingWithFile contentForWorkingWithFile = new ContentForWorkingWithFile(file);
        contentForWorkingWithFile.save(contentWithUnicode);
        String actual = contentForWorkingWithFile.read();
        Assert.assertEquals("Ùcontent",actual);
    }

    @Test
    public void ContentForWorkingWithFileWithoutUnicodeTest() throws IOException {

        Content contentWithoutUnicode = new ContentForWorkingWithFileWithoutUnicode(new ContentForWorkingWithFile(file));
        contentWithoutUnicode.save(contentWithUnicode);
        String actual = contentWithoutUnicode.read();
        Assert.assertEquals("contentWithoutUnicode",actual);
    }

    @After
    public void after(){
        file.delete();
    }

}
