package christmas.util;

import java.util.Arrays;
import java.util.List;

public class StringListUtil {

    public static List<String> seperateBy(final String input, final String seperateStandard) {
        return Arrays.stream(input.split(seperateStandard))
                .toList();
    }
}
