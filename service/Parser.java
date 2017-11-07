package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import model.ParserInterface;


public class Parser implements ParserInterface {

  private File file;

  public void setFile(File file) {
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public String getContent() throws IOException {
    FileInputStream inputStream = new FileInputStream(file);
    String output = "";
    int data;
    while ((data = inputStream.read()) > 0) {
      output += (char) data;
    }
    return output;
  }

  public void saveContent(String content) throws IOException {
    FileOutputStream o = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i++) {
      o.write(content.charAt(i));
    }
  }
}
