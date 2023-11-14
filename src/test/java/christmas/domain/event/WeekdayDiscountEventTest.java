package christmas.domain.event;

import christmas.domain.entity.event.Benefit;
import christmas.domain.entity.event.WeekdayDiscountEvent;
import christmas.domain.entity.menu.Items;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import christmas.domain.entity.Money;

class WeekdayDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-18, '초코케이크-1', 2023", // 월요일, 디저트 메뉴
            "2023-12-19, '초코케이크-1', 2023", // 화요일, 디저트 메뉴
            "2023-12-20, '초코케이크-1', 2023", // 수요일, 디저트 메뉴
            "2023-12-21, '초코케이크-1', 2023", // 목요일, 디저트 메뉴
            "2023-12-21, '초코케이크-5', 10115", // 목요일, 디저트 메뉴, 여러개
            "2023-12-21, '초코케이크-5,아이스크림-1', 12138", // 목요일, 디저트 메뉴, 여러개
            "2023-12-21, '초코케이크-5,해산물파스타-1', 10115", // 목요일, 디저트 메뉴, 여러개
            "2023-12-22, '티본스테이크-1', 0", // 금요일, 메인 메뉴
            "2023-12-23, '바비큐립-3', 0", // 토요일, 메인 메뉴
            "2023-12-18, '해산물파스타-2,바비큐립-1', 0",   // 월요일, 메인 메뉴 (할인 없음)
            "2023-12-23, '초코케이크-1', 0",       // 토요일, 디저트 메뉴 (할인 없음)
            "2023-12-23, '제로콜라-3', 0",
            "2024-12-23, '초코케이크-1', 0"
    })
    public void 평일할인_계산(final String date, final String itemNames, final int expectedDiscountAmount) {
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
