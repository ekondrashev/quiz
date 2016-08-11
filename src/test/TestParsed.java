package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.DefaultParsed;
import src.WithoutUnicodeParsed;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class TestParsed {

    @Test
    public void canReadFile() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultParsed defaultParsed=new DefaultParsed(file);
        String content=defaultParsed.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void inputWithUnicodeOutputWithoutUnicode() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultParsed defaultParsed=new DefaultParsed(file);
        WithoutUnicodeParsed withoutUnicodeParsed=new WithoutUnicodeParsed(defaultParsed);
        String contentWithUnicode=defaultParsed.read();
        String contentWithoutUnicode=withoutUnicodeParsed.read();
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
        DefaultParsed defaultParsed=new DefaultParsed(file);
        String content=defaultParsed.read();
        Assert.assertThat(content,is(""));
    }
    @Test
    public void saveAndGetContent() throws IOException {
        File file =new File("src\\test\\files\\saveAndGetContent.txt");
        DefaultParsed defaultParsed =new DefaultParsed(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";
        defaultParsed.save(incomeContent);
        String outputContent= defaultParsed.read();
        Assert.assertEquals(incomeContent,outputContent);
    }
}