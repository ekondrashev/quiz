package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.UsualStrings;
import src.WithoutUnicodeStrings;

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
        UsualStrings usualStrings =new UsualStrings(file);
        String content= usualStrings.showStrings();
        Assert.assertNotNull(content);
            }

    @Test
    public void inputWithUnicodeOutputWithoutUnicode() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        UsualStrings usualStrings =new UsualStrings(file);
        WithoutUnicodeStrings withoutUnicodeStrings =new WithoutUnicodeStrings(usualStrings);
        String contentWithUnicode= usualStrings.showStrings();
        String contentWithoutUnicode= withoutUnicodeStrings.showStrings();
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
        UsualStrings usualStrings =new UsualStrings(file);
        String content= usualStrings.showStrings();
        Assert.assertThat(content,is(""));
    }
   /* @Test
    public void saveAndGetContent() throws IOException {
        File file =new File("src\\test\\files\\saveAndGetContent.txt");
        UsualStrings usualStrings =new UsualStrings(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";

        usualStrings.saveStrings(incomeContent);
        String outputContent= usualStrings.showStrings();
        Assert.assertEquals(incomeContent,outputContent);
    }*/
}