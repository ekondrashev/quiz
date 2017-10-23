/**
 * Created by 4oc3p on 23.10.2017. quiz
 */
public abstract class AbstractContent implements ContentInterface {

    protected final FileContent fileContent;

    public AbstractContent(FileContent fileContent) {
        this.fileContent = fileContent;
    }

    public synchronized void write(String content) {
        fileContent.write(content);
    }

}
