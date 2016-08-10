package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.DefaultParser;
import src.WithoutUnicodeParser;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class TestParser {

    @Test
    public void canReadFile() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultParser defaultParser=new DefaultParser(file);
        String content=defaultParser.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void inputWithUnicodeOutputWithoutUnicode() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultParser defaultParser=new DefaultParser(file);
        WithoutUnicodeParser withoutUnicodeParser=new WithoutUnicodeParser(defaultParser);
        String contentWithUnicode=defaultParser.read();
        String contentWithoutUnicode=withoutUnicodeParser.read();
        boolean unicodeExistsInIncome=false;
        for (int i = 0; i <contentWithUnicode.length() ; i++) {
            if(contentWithUnicode.charAt(i)>=0x80){
                unicodeExistsInIncome=true;
                break;
            }
        }
        boolean unicodeAbsentInOutput=true;
        for (int i = 0; i <contentWithoutUnicode.length() ; i++) {
            if(contentWithoutUnicode.charAt(i)>=0x80){
                unicodeAbsentInOutput=false;
                break;
            }
        }
        Assert.assertThat(unicodeAbsentInOutput&&unicodeExistsInIncome,is(true));
    }
    @Test
    public void noContentInEmptyFile() throws IOException {
        File file =new File("src\\test\\files\\emptyFile.txt");
        DefaultParser defaultParser=new DefaultParser(file);
        String content=defaultParser.read();
        Assert.assertThat(content,is(""));
    }
    @Test
    public void saveAndGetContent() throws IOException {
        File file =new File("src\\test\\files\\saveAndGetContent.txt");
        DefaultParser defaultParser=new DefaultParser(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";
        defaultParser.save(incomeContent);
        String outputContent=defaultParser.read();
        Assert.assertEquals(incomeContent,outputContent);
    }
}