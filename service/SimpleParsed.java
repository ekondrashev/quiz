package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import model.ParsedInterface;


public class SimpleParsed implements ParsedInterface {

  private final File file;

  SimpleParsed(File file) {
    this.file = file;
  }

  public File giveFile() {
    return file;
  }

  public String giveContent() throws IOException {
    FileInputStream input = new FileInputStream(file);
    String output = "";
    int data;
    while ((data = input.read()) > 0) {
      output += (char) data;
    }
    return output;
  }

  public void saveContent(String content) throws IOException {
    FileOutputStream output = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i++) {
      output.write(content.charAt(i));
    }
  }
}
