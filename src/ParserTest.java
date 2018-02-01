import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ParserTest {
    private String WithUnicode = "ЙWithUnicode";
    private String WithoutUnicode = "WithoutUnicode";
    private File file;

    @Before
    public void init () {
        file = new File("tests/contentTest.txt");
    }
    @Test
   public void getContent() throws IOException {
        FileDataSource contentForWorkingWithFile = new FileDataSource(file);
                contentForWorkingWithFile.saveContent(WithUnicode);
                String actual = contentForWorkingWithFile.getContent();
                Assert.assertEquals("ЙWithUnicode",actual);
    }
    @Test
    public void getContentWithoutUnicode() throws IOException{
        Content content = new NoUnicode(new FileDataSource(file));
        content.saveContent(WithoutUnicode);
        String read = content.getContent();
        Assert.assertEquals("WithoutUnicode", read);
    }



    @After
     public void after(){
                file.delete();
    }
}