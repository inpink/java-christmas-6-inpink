package christmas.validation;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class IntegerValidatorTest {

    private final String expectedMessage = "잘못된 값입니다.";

    @ParameterizedTest
    @ValueSource(strings = {"k","2200000000"})
    public void 정수가_아니면_예외_처리(String input) {
        assertThatThrownBy(() -> IntegerValidator.validateInteger(input, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"20","-20","0","2100000000"})
    public void 정수가_맞으면_검증_통과(String input) {
        IntegerValidator.validateInteger(input, expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(ints = {-20,-2100000000})
    public void 음수면_예외_발생(int value) {
        assertThatThrownBy(() -> IntegerValidator.validateNotNegative(value, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @ValueSource(ints = {20,0,2100000000})
    public void 음수가_아니면_검증_통과(int value) {
        IntegerValidator.validateNotNegative(value, expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1, 100",
            "101, 1, 100",
            "100, 101, 1"
    })
    public void 범위_밖의_값이면_예외_발생(int value, int min, int max) {
        assertThatThrownBy(() -> IntegerValidator.validateInRange(value, min, max, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 1",
            "1, 1, 100",
            "2, 1, 3"
    })
    public void 범위_내의_값이면_정상_통과(int value, int min, int max) {
        IntegerValidator.validateInRange(value, min, max, expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "-1, 1"
    })
    public void 최소값보다_작으면_예외_발생(int value, int min) {
        assertThatThrownBy(() -> IntegerValidator.validateNotSmaller(value, min, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 0",
            "-1, -5"
    })
    public void 최소값보다_작지않으면_통과(int value, int min) {
        IntegerValidator.validateNotSmaller(value, min, expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "3, 1",
            "-1, -5"
    })
    public void 최대값보다_크면_예외_발생(int value, int max) {
        assertThatThrownBy(() -> IntegerValidator.validateNotBigger(value, max, expectedMessage))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectedMessage);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "-1, 5"
    })
    public void 최소값보다_크지않으면_통과(int value, int max) {
        IntegerValidator.validateNotBigger(value, max, expectedMessage);
    }

}
