package src.test;

import org.junit.Assert;
import org.junit.Test;
import src.FileDocument;
import src.NonUnicodeDocument;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.core.Is.is;

/**
 * Created by Антон on 01.08.2016.
 */
public class Tests {

    @Test
    public void canReadFileDocument() throws Exception {
        File file = new File("src\\test\\files\\fileWithUnicode.txt");
        FileDocument fileDocument = new FileDocument(file);
        CharSequence content = fileDocument.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void canReadNonUnicodeDocument() throws Exception {
        File file = new File("src\\test\\files\\fileWithUnicode.txt");
        NonUnicodeDocument nonUnicodeDocument = new NonUnicodeDocument(new FileDocument(file));
        CharSequence content = nonUnicodeDocument.read();
        Assert.assertNotNull(content);
    }

    @Test
    public void writtenContentEqualsToReadContent() throws IOException {
        File file = new File("src\\test\\files\\saveAndGetContent.txt");
        FileDocument fileDocument = new FileDocument(file);
        CharSequence savingContent = "so when i use FileInputStream for open stream to write,\n" +
                "constructor deletes the targetFile (makes it size equal to 0)\n" +
                "at once i create new FileInputStream object. Since, when i\n" +
                "use RandomAccessFile to open stream for writing nothing happens.\n";
        fileDocument.write(savingContent);
        CharSequence readContent = fileDocument.read();
        Assert.assertEquals(savingContent, readContent);
    }

    @Test
    public void readWithoutUnicode() throws Exception {
        File file = new File("src\\test\\files\\fileWithUnicode.txt");
        FileDocument fileDocument = new FileDocument(file);
        NonUnicodeDocument nonUnicodeDocument = new NonUnicodeDocument(fileDocument);
        CharSequence contentWithUnicode = fileDocument.read();
        CharSequence contentWithoutUnicode = nonUnicodeDocument.read();
        boolean unicodeExistsInFileDocument = false;
        for (int i = 0; i < contentWithUnicode.length(); i++) {
            if (contentWithUnicode.charAt(i) >= 0x80) {
                unicodeExistsInFileDocument = true;
                break;
            }
        }
        boolean unicodeAbsentInNonUnicodeDocument = true;
        for (int i = 0; i < contentWithoutUnicode.length(); i++) {
            if (contentWithoutUnicode.charAt(i) >= 0x80) {
                unicodeAbsentInNonUnicodeDocument = false;
                break;
            }
        }
        Assert.assertThat(unicodeAbsentInNonUnicodeDocument && unicodeExistsInFileDocument, is(true));
    }

    @Test(expected = FileNotFoundException.class)
    public void throwExceptionIfFileNotExists() throws IOException {
        File file = new File("src\\test\\files\\notExistingFile.txt");
        FileDocument fileDocument = new FileDocument(file);
        fileDocument.read();
    }
}