import org.junit.rules.*;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestParser {
   private final String fileName = "test.txt";
   private final String unicodeString = "Юникодтекст";
   private final String nonUnicodeString = "Simple text";
   @Rule
   public TemporaryFolder folder = new TemporaryFolder();
   public File saveStringToFile(String str) throws IOException {
      File f = this.folder.newFile(this.fileName);
      try {
         PrintWriter out = new PrintWriter(f.getAbsoluteFile());
         try {
            out.print(str);
         } finally {
            out.close();
         }
      } catch(IOException e) {
         throw new RuntimeException(e);
      }
      return f;
   }
   public String readFromFile(File f) throws IOException {
      String str = "";
      try {
         byte[] fArray = Files.readAllBytes(Paths.get(f.getPath()));
         str = new String(fArray, "UTF-8");
      } catch (IOException e) {
         System.err.println(e);
      }
      return str;
   }
   @Test
   public void testAccessors() throws IOException {
      Parser p = new Parser();
      p.setFile(this.folder.newFile(this.fileName));
      assertEquals(p.getFile().getName(), this.fileName);
   }
   @Test
   public void testGetContent_Simple() throws IOException {
      String str = this.nonUnicodeString;
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContent(), str);
   }
   @Ignore("Not running because <testing method incorrectly returns the Unicode string>")
   @Test
   public void testGetContent_Unicode() throws IOException {
      String str = this.unicodeString;
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContent(), str);
   }
   @Ignore("Not running because <testing method incorrectly returns the Unicode string>")
   @Test
   public void testGetContent_Mixed() throws IOException {
      String str = this.unicodeString + this.nonUnicodeString;
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContent(), str);
   }
   @Test
   public void testGetContent_None() throws IOException {
      String str = "";
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContent(), str);
   }
   @Test
   public void testGetContentWithoutUnicode_Simple() throws IOException {
      String str = this.nonUnicodeString;
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContentWithoutUnicode(), str);
   }
   @Test
   public void testGetContentWithoutUnicode_Mixed() throws IOException {
      Parser p = new Parser();
      p.setFile(saveStringToFile(this.unicodeString+this.nonUnicodeString));
      assertEquals(p.getContentWithoutUnicode(), this.nonUnicodeString);
   }
   @Test
   public void testGetContentWithoutUnicode_Unicode() throws IOException {
      Parser p = new Parser();
      p.setFile(saveStringToFile(this.unicodeString));
      assertEquals(p.getContentWithoutUnicode(), "");
   }
   @Test
   public void testGetContentWithoutUnicode_None() throws IOException {
      String str = "";
      Parser p = new Parser();
      p.setFile(saveStringToFile(str));
      assertEquals(p.getContentWithoutUnicode(), str);
   }
   @Test
   public void testSaveContent_Simple() throws IOException {
      Parser p = new Parser();
      p.setFile(this.folder.newFile(this.fileName));
      p.saveContent(this.nonUnicodeString);
      assertEquals(this.readFromFile(p.getFile()), nonUnicodeString);
   }
   @Ignore
   @Test
   public void testSaveContent_Unicode() throws IOException {
      Parser p = new Parser();
      p.setFile(this.folder.newFile(this.fileName));
      p.saveContent(this.unicodeString);
      assertEquals(this.readFromFile(p.getFile()), this.unicodeString);
   }
   @Ignore
   @Test
   public void testSaveContent_Mixed() throws IOException {
      Parser p = new Parser();
      p.setFile(this.folder.newFile(this.fileName));
      p.saveContent(this.unicodeString + this.nonUnicodeString);
      assertEquals(this.readFromFile(p.getFile()), this.unicodeString + this.nonUnicodeString);
   }
   @Test
   public void testSaveContent_None() throws IOException {
      Parser p = new Parser();
      p.setFile(this.folder.newFile(this.fileName));
      p.saveContent("");
      assertEquals(this.readFromFile(p.getFile()), "");
   }
}
