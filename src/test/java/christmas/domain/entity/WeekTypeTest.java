package christmas.domain.entity;

import christmas.domain.entity.WeekType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class WeekTypeTest {

    @ParameterizedTest(name = "{0}요일은 WeekType {1}이다.")
    @CsvSource({
            "MONDAY, WEEKDAY",
            "TUESDAY, WEEKDAY",
            "WEDNESDAY, WEEKDAY",
            "THURSDAY, WEEKDAY",
            "FRIDAY, WEEKEND",
            "SATURDAY, WEEKEND",
            "SUNDAY, WEEKDAY"
    })
    void 요일로_WeekType_찾기(final DayOfWeek dayOfWeek, final WeekType expectedWeekType) {
        // When
        final WeekType result = WeekType.findWeekType(dayOfWeek);

        // Then
        assertThat(result).isEqualTo(expectedWeekType);
    }

    @ParameterizedTest(name = "{0}은 WeekType {1}이다.")
    @CsvSource({
            "2023-01-02, WEEKDAY", // Monday
            "2023-01-03, WEEKDAY", // Tuesday
            "2023-01-04, WEEKDAY", // Wednesday
            "2023-01-05, WEEKDAY", // Thursday
            "2023-01-06, WEEKEND", // Friday
            "2023-01-07, WEEKEND", // Saturday
            "2023-01-08, WEEKDAY"  // Sunday
    })
    void 날짜로_WeekType_찾기(final String date, final WeekType expectedWeekType) {
        // When
        final WeekType result = WeekType.findWeekType(LocalDate.parse(date));

        // Then
        assertThat(result).isEqualTo(expectedWeekType);
    }
}
