/**
 * Created by 4oc3p on 23.10.2017. quiz
 */
public class AllContent extends AbstractContent {

    public AllContent(FileContent fileContent) {
        super(fileContent);
    }

    @Override
    public synchronized String read() {
        return super.fileContent.read();
    }

}
