package christmas.domain.event;

import christmas.domain.entity.Money;
import christmas.domain.entity.event.Benefit;
import christmas.domain.entity.event.ChristmasDayDiscountEvent;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ChristmasDayDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-01, 1000", // 할인 시작일
            "2023-12-02, 1100", // 시작일 다음날
            "2023-12-10, 1900", // 할인 기간 중
            "2023-12-25, 3400", // 할인 마지막 날
            "2023-11-30, 0",    // 할인 시작 전
            "2023-12-26, 0"     // 할인 종료 후
    })
    void 크리스마스_당일_할인_금액(final String dateStr, final int expectedAmount) {
        // Given
        final LocalDate date = LocalDate.parse(dateStr);

        // When
        final Benefit benefit = ChristmasDayDiscountEvent.calculateBenefit(date);
        final Money discountPrice = benefit.getDiscountPrice();

        // Then
        assertThat(discountPrice.getAmount()).isEqualTo(expectedAmount);
    }
}
