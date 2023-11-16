package christmas.domain.entity;


import static christmas.messages.ErrorMessages.INVALID_DATE;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

import christmas.util.ExceptionUtil;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.EnumSet;
import java.util.stream.Stream;

public enum WeekType {
    WEEKDAY(EnumSet.of(SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY)),
    WEEKEND(EnumSet.of(FRIDAY, SATURDAY));

    private final EnumSet<DayOfWeek> days;

    WeekType(final EnumSet<DayOfWeek> days) {
        this.days = days;
    }

    public static WeekType findWeekType(final DayOfWeek day) {
        return Stream.of(WeekType.values())
                .filter(weekType -> weekType.contains(day))
                .findFirst()
                .orElseThrow(()
                        -> ExceptionUtil.returnInvalidValueException(INVALID_DATE.getMessage()));
    }

    public static WeekType findWeekType(final LocalDate date) {
        return findWeekType(date.getDayOfWeek());
    }

    public boolean contains(final DayOfWeek day) {
        return days.contains(day);
    }
}