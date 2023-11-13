package christmas.domain.event;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class StarredTest {

    @ParameterizedTest(name = "{0}는 Starred {1}다.")
    @CsvSource({
            "2023-12-03, YES",
            "2023-12-10, YES",
            "2023-12-17, YES",
            "2023-12-24, YES",
            "2023-12-31, YES",
            "2023-12-25, YES",
            "2023-12-01, NO",
            "2023-12-02, NO",
            "2023-12-04, NO"
    })
    void 날짜가_어떤_Starred인지_찾기(final LocalDate date, final Starred expected) {
        final Starred result = Starred.findStarred(date);
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}는 Starred되었다 문장은 {1}다.")
    @CsvSource({
            "2023-12-03, true",
            "2023-12-10, true",
            "2023-12-17, true",
            "2023-12-24, true",
            "2023-12-31, true",
            "2023-12-25, true",
            "2023-12-01, false",
            "2023-12-02, false",
            "2023-12-04, false"
    })
    void 날짜가_starred인지_상태_확인(final LocalDate date, final boolean expected) {
        final boolean isStarred = Starred.isStarred(date);
        assertThat(isStarred).isEqualTo(expected);
    }
}
