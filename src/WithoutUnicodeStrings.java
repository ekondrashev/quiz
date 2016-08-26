package src;

import java.io.IOException;

/**
 * Created by Антон on 04.08.2016.
 */
public final class WithoutUnicodeStrings implements ParsableStrings {
    private ParsableStrings parsableStrings;
    private String content;

    public WithoutUnicodeStrings(ParsableStrings parsableStrings) {
        this.parsableStrings = parsableStrings;
        content = "";
    }

    @Override
    public String showStrings() throws IOException {
        if (content.isEmpty()) {
            String strings = parsableStrings.showStrings();
            StringBuilder contentWithoutUnicode = new StringBuilder();
            for (int i = 0; i < strings.length(); i++) {
                char symbol = strings.charAt(i);
                if (symbol < 0x80) {
                    contentWithoutUnicode.append(symbol);
                }
            }
            content = contentWithoutUnicode.toString();
        }
        return content;
    }
}
