package model;

import java.io.File;
import java.io.IOException;

public interface ParsedInterface {

  File giveFile();

  String giveContent() throws IOException;

  void saveContent(String content) throws IOException;

}
