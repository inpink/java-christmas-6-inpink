package christmas.domain.event;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import christmas.domain.money.DiscountPrice;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BadgeTest {

    @ParameterizedTest(name = "총 할인 혜택 {0}원은 {1} 뱃지다.")
    @CsvSource({
            "0, NO",
            "1, NO",
            "4999, NO",
            "5000, STAR",
            "5001, STAR",
            "9999, STAR",
            "10000, TREE",
            "10001, TREE",
            "19999, TREE",
            "20000, SANTA",
            "20001, SANTA",
            "2100000000, SANTA"
    })
    public void 정상_뱃지_반환(final int price, final String expectedBadgeName) {
        // When
        final DiscountPrice discountPrice = new DiscountPrice(price);
        final Badge result = Badge.getBadgeByPrice(discountPrice);

        // Then
        assertThat(result.name()).isEqualTo(expectedBadgeName);
    }
}
