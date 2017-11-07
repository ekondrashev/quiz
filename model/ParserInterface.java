package model;

import java.io.File;
import java.io.IOException;

public interface ParserInterface {

  void setFile(File file);

  File getFile();

  String getContent() throws IOException;

  void saveContent(String content) throws IOException;

}
