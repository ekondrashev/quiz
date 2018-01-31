import java.io.File;
import java.io.IOException;

public class ContentDecorator implements Content {
    private Content wrapper;
    protected File file;

    public ContentDecorator(File file, Content wrapper) {
        this.file = file;
        this.wrapper= wrapper;
    }

    @Override
    public String getContent() throws IOException {
        return wrapper.getContent();
    }


    @Override
    public void saveContent(String content) throws IOException {
    wrapper.saveContent(content);
    }
}
