package model;

import java.io.File;
import java.io.IOException;

public interface ParsedInterface {

  String content() throws IOException;

  void saveContent(String content) throws IOException;

}
