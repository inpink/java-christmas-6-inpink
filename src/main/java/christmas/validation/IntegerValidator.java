package christmas.validation;

import christmas.util.ExceptionUtil;
import christmas.util.IntegerUtil;
import java.math.BigInteger;
import java.util.Arrays;

public final class IntegerValidator {

    public static void validateInteger(final String string, final String errorMessage) {
        if (!IntegerUtil.isInteger(string)) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

    public static void validateInteger(final String errorMessage, final String... strings) {
        Arrays.stream(strings)
                .forEach(string -> validateInteger(string, errorMessage));
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

    public static void validatePlusRange(final int value1, final int value2, final String errorMessage) {
        final BigInteger a = BigInteger.valueOf(value1);
        final BigInteger b = BigInteger.valueOf(value2);
        final BigInteger result = a.add(b);

        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }

    public static void validateSame(final int value1, final int value2, final String errorMessage) {
        if (value1 != value2) {
            ExceptionUtil.throwInvalidValueException(errorMessage);
        }
    }
}
