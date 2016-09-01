package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.Document;
import src.NonUnicodeDocument;

import java.io.File;

import static org.hamcrest.core.Is.is;


/**
 * Created by Антон on 01.08.2016.
 */
public class Tests {

    @Test
    public void readFile() throws Exception {
        File file = new File("src\\test\\files\\someFile.txt");
        Document document = new Document(file);
        CharSequence content = document.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void inputOutput() throws Exception {
        File file = new File("src\\test\\files\\someFile.txt");
        Document document = new Document(file);
        NonUnicodeDocument nonUnicodeDocument = new NonUnicodeDocument(document);
        CharSequence contentWithUnicode = document.read();
        CharSequence contentWithoutUnicode = nonUnicodeDocument.read();
        boolean unicodeExistsInIncome = false;
        for (int i = 0; i < contentWithUnicode.length(); i++) {
            if (contentWithUnicode.charAt(i) >= 0x80) {
                unicodeExistsInIncome = true;
                break;
            }
        }
        boolean unicodeAbsentInOutput = true;
        for (int i = 0; i < contentWithoutUnicode.length(); i++) {
            if (contentWithoutUnicode.charAt(i) >= 0x80) {
                unicodeAbsentInOutput = false;
                break;
            }
        }
        Assert.assertThat(unicodeAbsentInOutput && unicodeExistsInIncome, is(true));
    }
}