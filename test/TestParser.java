import java.io.*;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestParser {
   private final String fileName = "test.txt";
   private final String unicodeString = "Юникодтекст";
   private final String nonUnicodeString = "Simple text";
   public void saveStringToFile(String str) {
      File f = new File( this.fileName );
      try {
         f.createNewFile();
         PrintWriter out = new PrintWriter(f.getAbsoluteFile());
         try {
            out.print( str );
         } finally {
            out.close();
         }
      } catch(IOException e) {
         throw new RuntimeException(e);
      }
   }
   public String readFromFile() throws IOException {
      File f = new File( this.fileName );
      StringBuilder sb = new StringBuilder();
      if(f.exists() & f.canRead() & f.isFile()) {
         BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f),"UTF-8"));
         String s = "";
         while((s = br.readLine()) != null)
            sb.append(s);
         br.close();
      }
      return new String(sb);
   }
   @After
   public void teardownDeleteFile() {
      File f = new File( this.fileName );
      if (f.exists() & f.isFile()) {
         f.delete();
      }
   }
   @Test
   public void testAccessors() {
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getFile().getName(), this.fileName );
   }
   @Test
   public void testGetContent_Simple() throws IOException {
      String str = this.nonUnicodeString;
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContent(), str );
   }
   @Ignore("Not running because <testing method incorrectly returns the Unicode string>")
   @Test
   public void testGetContent_Unicode() throws IOException {
      String str = this.unicodeString;
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContent(), str );
   }
   @Ignore("Not running because <testing method incorrectly returns the Unicode string>")
   @Test
   public void testGetContent_Mixed() throws IOException {
      String str = this.unicodeString + this.nonUnicodeString;
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContent(), str );
   }
   @Test
   public void testGetContent_None() throws IOException {
      String str = "";
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContent(), str );
   }
   @Test
   public void testGetContentWithoutUnicode_Simple() throws IOException {
      String str = this.nonUnicodeString;
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContentWithoutUnicode(), str );
   }
   @Test
   public void testGetContentWithoutUnicode_Mixed() throws IOException {
      saveStringToFile( this.unicodeString + this.nonUnicodeString );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContentWithoutUnicode(), this.nonUnicodeString );
   }
   @Test
   public void testGetContentWithoutUnicode_Unicode() throws IOException {
      saveStringToFile( this.unicodeString );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContentWithoutUnicode(), "" );
   }
   @Test
   public void testGetContentWithoutUnicode_None() throws IOException {
      String str = "";
      saveStringToFile( str );
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      assertEquals( p.getContentWithoutUnicode(), str );
   }
   @Test
   public void testSaveContent_Simple() throws IOException {
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      p.saveContent( this.nonUnicodeString );
      assertEquals( this.readFromFile(), nonUnicodeString );
   }
   @Ignore
   @Test
   public void testSaveContent_Unicode() throws IOException {
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      p.saveContent( this.unicodeString );
      assertEquals( this.readFromFile(), this.unicodeString );
   }
   @Ignore
   @Test
   public void testSaveContent_Mixed() throws IOException {
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      p.saveContent( this.unicodeString + this.nonUnicodeString );
      assertEquals( this.readFromFile(), this.unicodeString + this.nonUnicodeString );
   }
   @Test
   public void testSaveContent_None() throws IOException {
      Parser p = new Parser();
      p.setFile( new File( this.fileName ) );
      p.saveContent( "" );
      assertEquals( this.readFromFile(), "" );
   }
}
