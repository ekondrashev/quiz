package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.DefaultDocument;
import src.WithoutUnicodeDocument;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class Tests {

    @Test
    public void canReadFile() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultDocument defaultDocument=new DefaultDocument(file);
        String content=defaultDocument.read();
        Assert.assertNotNull(content);
            }

    @Test
    public void inputWithUnicodeOutputWithoutUnicode() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        DefaultDocument defaultDocument=new DefaultDocument(file);
        WithoutUnicodeDocument withoutUnicodeDocument=new WithoutUnicodeDocument(defaultDocument);
        String contentWithUnicode=defaultDocument.read();
        String contentWithoutUnicode=withoutUnicodeDocument.read();
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
        DefaultDocument defaultDocument=new DefaultDocument(file);
        String content=defaultDocument.read();
        Assert.assertThat(content,is(""));
    }
    @Test
    public void saveAndGetContent() throws IOException {
        File file =new File("src\\test\\files\\saveAndGetContent.txt");
        DefaultDocument defaultDocument =new DefaultDocument(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";
        defaultDocument.save(incomeContent);
        String outputContent= defaultDocument.read();
        Assert.assertEquals(incomeContent,outputContent);
    }
}