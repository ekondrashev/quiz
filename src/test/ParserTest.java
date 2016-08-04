package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.Parser;
import src.ParserWithoutUnicode;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class ParserTest {

    @Test
    public void notEmptyContentIfFileNotEmpty() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Parser parser=new Parser(file);
        String content=parser.readContent();
        Assert.assertNotNull(content);
    }

    @Test
    public void outputContentWithoutUnicodeIfInputFileWithUnicodeSymbol() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Parser parser=new Parser(file);
        ParserWithoutUnicode parserWithoutUnicode=new ParserWithoutUnicode(parser);
        String contentWithUnicode=parser.readContent();
        String contentWithoutUnicode=parserWithoutUnicode.readContent();
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
    public void emptyContentIfFileEmpty() throws IOException {
        File file =new File("src\\test\\files\\emptyFile.txt");
        Parser parser=new Parser(file);
        String content=parser.readContent();
        Assert.assertThat(content,is(""));
    }
    @Test
    public void saveContentAndGetTheSameResult() throws IOException {
        File file =new File("src\\test\\files\\saveContentAndGetTheSameResult.txt");
        Parser parser=new Parser(file);
        String incomeContent="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";
        parser.saveContent(incomeContent);
        String outputContent=parser.readContent();
        Assert.assertEquals(incomeContent,outputContent);
    }
}