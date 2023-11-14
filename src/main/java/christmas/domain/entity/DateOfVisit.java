package christmas.domain.entity;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import christmas.validation.DateValidator;
import christmas.validation.IntegerValidator;
import java.time.LocalDate;

public class DateOfVisit {
    private final int date;

    private DateOfVisit(final int date) {
        this.date = date;
    }

    public static LocalDate toLocalDate(final String unverifiedDate) {
        IntegerValidator.validateInteger(unverifiedDate);
        DateValidator.validateExistInCalendar(
                THIS_YEAR.toString(),
                THIS_MONTH.toString(),
                unverifiedDate
        );
        return LocalDate.of(
                THIS_YEAR.getValue(),
                THIS_MONTH.getValue(),
                Integer.parseInt(unverifiedDate)
        );
    }
}
