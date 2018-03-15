import java.io.File;
import java.io.IOException;

public abstract class AbstractContentWriter {

    protected File file;

    public AbstractContentWriter(File file) {
        this.file = file;
    }

    public abstract void save(String content) throws IOException;

}
