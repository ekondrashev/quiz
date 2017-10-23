/**
 * Created by 4oc3p on 23.10.2017. quiz
 */
public class ContentWithoutUnicode extends AbstractContent {

    public ContentWithoutUnicode(FileContent fileContent) {
        super(fileContent);
    }

    @Override
    public String read() {
        return super.fileContent.read().replaceAll("[^\\x00-\\x7F]", "");
    }

}
