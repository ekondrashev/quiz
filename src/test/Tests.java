package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.Document;
import src.NonUnicode;

import java.io.File;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class Tests {

    @Test
    public void readFile() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Document document =new Document(file);
        String content= document.parse();
        Assert.assertNotNull(content);
            }

    @Test
    public void inputOutput() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Document document =new Document(file);
        NonUnicode nonUnicode =new NonUnicode(document);
        String contentWithUnicode= document.parse();
        String contentWithoutUnicode= nonUnicode.parse();
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

   /* @Test
    public void saveAndGetContent() throws IOException {
        File file =new File("src\\test\\files\\saveAndGetContent.txt");
        Document usualStrings =new Document(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";

        usualStrings.saveStrings(incomeContent);
        String outputContent= usualStrings.parse();
        Assert.assertEquals(incomeContent,outputContent);
    }*/
}