package christmas.validation;

import christmas.util.ExceptionUtil;
import java.util.Map;

public final class MapValidator {

    public static void validateDuplicateKey(final Map map, final Object item, final String errorMessage ) {
        if (map.containsKey(item)) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }


}
