package christmas.validation;

import christmas.util.ExceptionUtil;
import christmas.util.IntegerUtil;
import java.math.BigInteger;

public class IntegerValidator {

    public static void validateInteger(final String string, final String errorMessage) {
        if (!IntegerUtil.isInteger(string)) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

    public static void validateNotNegative(final int value, final String errorMessage) {
        if (value < 0) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

    public static void validateInRange(final int value, final int min, final int max, final String errorMessage) {
        validateNotSmaller(value, min, errorMessage);
        validateNotBigger(value, max, errorMessage);
    }

    public static void validateNotSmaller(final int value, final int min, final String errorMessage) {
        if (value < min) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

    public static void validateNotBigger(final int value, final int max, final String errorMessage) {
        if (value > max) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

}
