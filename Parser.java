import java.io.File;
import java.io.IOException;

public interface Parser {

  void setFile(File f);

  File getFile();

  String getContent() throws IOException;

  String getContentWithoutUnicode() throws IOException;

  void saveContent(String content) throws IOException;
}
