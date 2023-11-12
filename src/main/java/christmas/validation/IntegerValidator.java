package christmas.validation;

import static christmas.messages.ErrorMessages.INVALID_NEGATIVE_VALUE_MESSAGE;

import christmas.util.ExceptionUtil;
import christmas.util.IntegerUtil;

public class IntegerValidator {

    public static void validateNotNegative(int value) {
        if (IntegerUtil.isNegative(value)) {
            ExceptionUtil.throwInvalidValueException(INVALID_NEGATIVE_VALUE_MESSAGE.getMessage());
        }
    }
}
