
import java.io.File;
import java.io.IOException;

public abstract class AbstractContentReader {

    protected File file;

    public AbstractContentReader(File file) {
        this.file = file;
    }
    
    public abstract String content() throws IOException;

}
