package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.Parser;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class ParserTest {
    @Test
    public void fileIsNullWhenParserAlreadyCreated() throws Exception {
        Parser parser=new Parser();
        Assert.assertEquals(null,parser.getFile());
    }

    @Test
    public void notNullFileIfFileExists(){
        File file =new File("src\\test\\files\\someFile.txt");
        Parser parser=new Parser();
        parser.setFile(file);
        Assert.assertThat(file,is(parser.getFile()));
    }
    @Test
    public void notEmptyContentIfFileNotEmpty() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Parser parser=new Parser();
        parser.setFile(file);
        String content=parser.getContent();
        Assert.assertNotNull(content);
    }

    @Test
    public void outputContentWithoutUnicodeIfInputFileWithUnicodeSymbol() throws Exception {
        File file =new File("src\\test\\files\\someFile.txt");
        Parser parser=new Parser();
        parser.setFile(file);
        String contentWithUnicode=parser.getContent();
        String contentWithoutUnicode=parser.getContentWithoutUnicode();
        boolean unicodeExistsInIncome=false;
        for (int i = 0; i <contentWithoutUnicode.length() ; i++) {
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
        Parser parser=new Parser();
        parser.setFile(file);
        String content=parser.getContent();
        Assert.assertThat(content,is(""));
    }
    @Test
    public void saveContentAndGetTheSameResult() throws IOException {
        File file =new File("src\\test\\files\\saveContentAndGetTheSameResult.txt");
        Parser parser=new Parser();
        parser.setFile(file);
        String content="fgfg gfdg fd gf g fdg fdgdnjgadjiohui\n rknarjngjjgfdgjkdlgjk" +
                "fdgfdgfdjgkljfdkgljfdkgljfdg\n" +
                "fgfdgklfgjklfjgfdg" +
                "gffdgjklfdg";
        parser.saveContent(content);
        String gottenContent=parser.getContent();
        Assert.assertEquals(content,gottenContent);
    }
}