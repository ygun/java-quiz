import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * This class is thread safe.
 */
public class Lexer {
  private File input;
  public synchronized void setFile(File f) {
    input = f;
  }
  public synchronized File file() {
    return input;
  }
  public String readContent() throws IOException {
    FileInputStream i = new FileInputStream(input);
    String t = "";
    int data;
    while ((data = i.read()) > 0) {
      t += (char) data;
    }
    return t;
  }
  public String readContentWOUnicode() throws IOException {
    FileInputStream i = new FileInputStream(input);
    String temp = "";
    int data;
    while ((data = i.read()) > 0) {
      if (data < 0x80) {
        temp += (char) data;
      }
    }
    return temp;
  }
  public void saveContent(String text) {
    FileOutputStream o = new FileOutputStream(input);
    try {
      for (int i = 0; i < text.length(); i += 1) {
        o.write(text.charAt(i));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
