package christmas.domain.event;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import christmas.domain.entity.Money;

class GiftEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-10, 12000, 1", // 이벤트 기간 내, 최소 주문 금액 충족
            "2023-12-26, 12000, 0", // 이벤트 기간 이후, 최소 주문 금액 충족
            "2023-12-10, 11999, 0", // 이벤트 기간 내, 최소 주문 금액 미충족
            "2023-11-30, 12000, 0"  // 이벤트 기간 이전, 최소 주문 금액 충족
    })
    void 증정_이벤트_계산(final String date, final int totalOrderPrice, final int expectedResult) {
        // Given
        final LocalDate testDate = LocalDate.parse(date);
        final Money orderPrice = Money.create(totalOrderPrice);

        // When
        Benefit benefit = GiftEvent.calculateBenefit(testDate, orderPrice);
        Gifts gifts = benefit.getGifts();

        // Then
        assertThat(gifts.size()).isEqualTo(expectedResult);
    }
}
