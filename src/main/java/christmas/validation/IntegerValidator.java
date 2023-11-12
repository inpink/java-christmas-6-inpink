package christmas.validation;

import christmas.util.ExceptionUtil;
import christmas.util.IntegerUtil;
import java.math.BigInteger;

public class IntegerValidator {

    public static void validateInteger(final String string, final String errorMessage) {
        if (!IntegerUtil.isInteger(string)) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }  //


}
