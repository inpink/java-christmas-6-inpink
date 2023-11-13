package christmas.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilTest {

    @ParameterizedTest(name = "정수 {0}는 천단위 구분자 문자열로 {1}다.")
    @CsvSource({
            "'1000', '1,000'",
            "'1000000', '1,000,000'",
            "'123456789', '123,456,789'",
            "'0', '0'"
    })
    void 천단위_구분자_문자열_생성(int number, String expected) {
        // When
        String result = StringUtil.formatByThousandSeparator(number);

        // Then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "정수 {0}의 모든 공백을 제거하면 {1}다.")
    @CsvSource({
            "'Hello World', 'HelloWorld'",
            "' Java Programming ', 'JavaProgramming'",
            "'123 456 789', '123456789'",
            "'', ''"
    })
    void 문자열의_모든_공백을_제거(String input, String expected) {
        // When
        String result = StringUtil.removeAllSpaces(input);

        // Then
        assertThat(StringUtil.removeAllSpaces(result)).isEqualTo(expected);
    }

}