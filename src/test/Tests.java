package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.EntireDocument;
import src.NonUnicodeDocument;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;

/**
 * Created by Антон on 01.08.2016.
 */
public class Tests {

    @Test
    public void readFile() throws Exception {
        File file = new File("src\\test\\files\\someFile.txt");
        EntireDocument entireDocument = new EntireDocument(file);
        CharSequence content = entireDocument.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void inputOutput() throws Exception {
        File file = new File("src\\test\\files\\someFile.txt");
        EntireDocument entireDocument = new EntireDocument(file);
        NonUnicodeDocument nonUnicodeDocument = new NonUnicodeDocument(entireDocument);
        CharSequence contentWithUnicode = entireDocument.read();
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

    @Test
    public void save() throws IOException {
        File file = new File("src\\test\\files\\saveAndGetContent.txt");
        EntireDocument entireDocument = new EntireDocument(file);
        NonUnicodeDocument nonUnicodeDocument = new NonUnicodeDocument(entireDocument);
        CharSequence contentToSave = "so when i use FileInputStream for open stream to write,\n" +
                "constructor deletes the targetFile (makes it size equal to 0)\n" +
                "at once i create new FileInputStream object. Since, when i\n" +
                "use RandomAccessFile to open stream for writing nothing happens.\n";
        nonUnicodeDocument.save(contentToSave);
        CharSequence resultContentEntire = entireDocument.read();
        Assert.assertEquals(contentToSave, resultContentEntire);
    }
}