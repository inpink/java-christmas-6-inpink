package christmas.validation;

import static christmas.messages.ErrorMessages.INVALID_DATE;

import christmas.util.ExceptionUtil;
import java.time.DateTimeException;
import java.time.LocalDate;

public final class DateValidator {

    public static void validateExistInCalendar(final String year,
                                               final String month,
                                               final String date) {
        IntegerValidator.validateInteger(year, month, date);

        try {
            LocalDate.of(Integer.parseInt(year),
                    Integer.parseInt(month),
                    Integer.parseInt(date));
        } catch (DateTimeException e) {
            ExceptionUtil.throwInvalidValueException(INVALID_DATE.getMessage());
        }
    }
}
