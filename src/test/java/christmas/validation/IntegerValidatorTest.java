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

}
