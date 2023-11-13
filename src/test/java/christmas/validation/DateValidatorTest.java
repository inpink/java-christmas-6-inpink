package christmas.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateValidatorTest {

    private final String expectedMessage = "잘못된 값입니다.";

    @ParameterizedTest(name = "{0}년 {1}월 {2}일은 옳은 날짜다.")
    @CsvSource({
            "2023, 12, 25",
            "2020, 2, 29",  // 윤년
            "2021, 1, 31",
            "2021, 01, 01"
    })
    void 달력에_있는_옳은_날짜_검증_통과(String year, String month, String day) {
        // When
        DateValidator.validateExistInCalendar(year, month, day, expectedMessage);
    }

    @ParameterizedTest(name = "{0}년 {1}월 {2}일은 옳은 날짜가 아니다.")
    @CsvSource({
            "2023, 12, 0",
            "2023, 12, 32",
            "2023, 2, 30",
            "2021, 4, 31",
            "2020, 11, 31",
            "2200000000, 1, 1"
    })
    void 달력에_없는_옳지않은_날짜는_예외처리(String year, String month, String day) {
        // When & Then
        assertThatThrownBy(() -> DateValidator.validateExistInCalendar(year, month, day, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }
}
