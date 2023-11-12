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

}
