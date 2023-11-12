package christmas.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class IntegerUtilTest {

    @ParameterizedTest
    @CsvSource({
            "2000000000, true",     // 정상 범위의 정수
            "-2000000000, true",    // 음수 정수
            "0, true",              // 0
            "2200000000, false",    // 정수 범위 초과
            "hi, false"             // 숫자가 아닌 값
    })
    void testIsInteger(String input, boolean expected) {
        // When
        boolean result = IntegerUtil.isInteger(input);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}