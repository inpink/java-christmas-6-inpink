package christmas.domain.event;

import christmas.domain.entity.Money;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;

class SpecialDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-03, 1000",
            "2023-12-10, 1000",
            "2023-12-17, 1000",
            "2023-12-24, 1000",
            "2023-12-25, 1000",
            "2023-12-31, 1000",
            "2023-12-30, 0",
            "2023-11-30, 0",
            "2024-01-01, 0"
    })
    void testCalculateSpecialBenefit(final String date, final int expectedDiscountAmount) {
        // Given
        final LocalDate testDate = LocalDate.parse(date);

        // When
        final Benefit benefit = SpecialDiscountEvent.calculateBenefit(testDate);
        final Money result = benefit.getDiscountPrice();

        // Then
        assertThat(result.getAmount()).isEqualTo(expectedDiscountAmount);
    }
}
