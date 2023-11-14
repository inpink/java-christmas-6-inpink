package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.entity.Money;
import christmas.domain.menu.Items;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-18, '초코케이크-1', 2023", // 월요일, 디저트 메뉴
            "2023-12-19, '아이스크림-2', 4046", // 화요일, 디저트 메뉴, 여러개
            "2023-12-20, '타파스-1', 0", // 수요일, 애피타이저 메뉴 (할인 없음)
            "2023-12-21, '티본스테이크-1', 0", // 목요일, 메인 메뉴 (할인 없음)
            "2023-12-22, '제로콜라-3', 0", // 금요일, 음료 메뉴 (할인 없음)
            "2023-12-23, '초코케이크-1', 0", // 토요일, 디저트 메뉴 (할인 없음)
            "2023-12-24, '크리스마스파스타-1', 0", // 일요일, 메인 메뉴 (할인 없음)
            "2023-12-18, '초코케이크-1,아이스크림-2', 6069", // 월요일, 디저트 메뉴, 복합 케이스
    })
    public void 주말할인_계산(final String date, final String itemNames, final int expectedDiscountAmount) {
        // Given
        final LocalDate testDate = LocalDate.parse(date);
        final Items items = Items.create(itemNames);

        // When
        final Benefit benefit = WeekdayDiscountEvent.calculateBenefit(testDate, items);
        final Money result = benefit.getDiscountPrice();

        // Then
        assertThat(result.getAmount()).isEqualTo(expectedDiscountAmount);
    }
}
