package christmas.domain.money;

import christmas.util.ExceptionUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.messages.ErrorMessages.INVALID_DIVISION_BY_ZERO_MESSAGE;
import static christmas.messages.ErrorMessages.INVALID_NEGATIVE_VALUE_MESSAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    private static class TestMoney extends Money {
        public TestMoney(final int amount) {
            super(amount);
        }

        @Override
        protected TestMoney create(final int amount) {
            return new TestMoney(amount);
        }
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 200, 2100000000, 0})
    public void 정상_Money_자식_객체_생성(int amount) {
        // When
        Money money = new TestMoney(amount);

        // Then
        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2100000000})
    public void Money는_음수값을_가질_수_없음(int amount) {
        // When && Then
        assertThatThrownBy(() -> new TestMoney(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NEGATIVE_VALUE_MESSAGE.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "100, 100, true", // 같음
            "100, 50, true", // 더 큼
            "100, 150, false" // 더 작음
    })
    public void 더_크거나_같은_값인지_상태값_반환(int amount, int compareTo, boolean expected) {
        // Given
        Money money = new TestMoney(amount);

        // When && Then
        assertThat(money.isBiggerOrSameThan(compareTo)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 150, true", // 더 작음
            "100, 100, false", // 같음
            "100, 50, false" // 더 큼
    })
    public void 더_작은_값인지_상태값_반환(int amount, int compareTo, boolean expected) {
        // Given
        Money money = new TestMoney(amount);

        // When && Then
        assertThat(money.isSmallerThan(compareTo)).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 50, 150",  // 정상적인 더하기
            "0, 0, 0"        // 0 더하기
    })
    void Money끼리_더하면_새로운_Money객체(int amount1, int amount2, int expected) {
        // Given
        TestMoney money1 = new TestMoney(amount1);
        TestMoney money2 = new TestMoney(amount2);

        // When
        TestMoney result = (TestMoney) money1.add(money2);

        // Then
        assertThat(money1).isNotSameAs(result);
        assertThat(money2).isNotSameAs(result);
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 50, 50",   // 정상적인 빼기
            "0, 0, 0"        // 0 빼기
    })
    void Money끼리_빼면_새로운_Money객체(int amount1, int amount2, int expected) {
        // Given
        TestMoney money1 = new TestMoney(amount1);
        TestMoney money2 = new TestMoney(amount2);

        // When
        TestMoney result = (TestMoney) money1.subtract(money2);

        // Then
        assertThat(money1).isNotSameAs(result);
        assertThat(money2).isNotSameAs(result);
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "100, 10, 10",
            "10, 1, 10",
            "0, 100, 0"
    })
    void Money끼리_나누면_새로운_Money객체(int amount1, int amount2, int expected) {
        // Given
        TestMoney money1 = new TestMoney(amount1);
        TestMoney money2 = new TestMoney(amount2);

        // When
        TestMoney result = (TestMoney) money1.divide(money2);

        // Then
        assertThat(result.getAmount()).isEqualTo(expected);
    }

}
