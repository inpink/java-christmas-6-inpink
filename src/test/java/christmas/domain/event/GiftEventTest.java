package christmas.domain.event;

import christmas.domain.menu.Item;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import christmas.domain.entity.Money;

class GiftEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-10, 120000, 1, 25000", // 이벤트 기간 내, 최소 주문 금액 충족
            "2023-12-26, 120000, 1, 25000", // 이벤트 기간 내, 최소 주문 금액 충족
            "2023-12-10, 119999, 0, 0", // 이벤트 기간 내, 최소 주문 금액 미충족
            "2024-01-26, 120000, 0, 0", // 이벤트 기간 이후, 최소 주문 금액 충족
            "2023-11-30, 120000, 0, 0"  // 이벤트 기간 이전, 최소 주문 금액 충족
    })
    public void 증정_이벤트_계산(final String date,
                          final int totalOrderPrice,
                          final int expectedGiftsSize,
                          final int expectedDiscountPrice) {
        // Given
        final LocalDate testDate = LocalDate.parse(date);
        final Money orderPrice = Money.create(totalOrderPrice);

        // When
        final Benefit benefit = GiftEvent.calculateBenefit(testDate, orderPrice);
        final List<Item> gifts = benefit.getGiftsByList();
        final Money money = benefit.getDiscountPrice();

        // Then
        assertThat(gifts.size()).isEqualTo(expectedGiftsSize);
        assertThat(money.getAmount()).isEqualTo(expectedDiscountPrice);
    }
}
