package christmas.domain.entity;


import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;

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

}