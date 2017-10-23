import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by 4oc3p on 24.10.2017. quiz
 */
public final class FileContent implements ContentInterface {

    private final String path;

    public FileContent(String path) {
        this.path = path;
    }

    @Override
    public synchronized String read() {
        try {
            return new Scanner(new File(path)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized void write(String content) {
        try {
            PrintWriter out = new PrintWriter(path);
            out.write(content);
            out.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
