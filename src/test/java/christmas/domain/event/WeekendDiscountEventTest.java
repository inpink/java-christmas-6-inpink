package christmas.domain.event;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.entity.Money;
import christmas.domain.menu.Menu;
import java.time.LocalDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WeekendDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-18, DESSERTS, 0", // 월요일, 디저트 메뉴
            "2023-12-19, DESSERTS, 0", // 화요일, 디저트 메뉴
            "2023-12-20, DESSERTS, 0", // 수요일, 디저트 메뉴
            "2023-12-21, DESSERTS, 0", // 목요일, 디저트 메뉴
            "2023-12-22, MAIN_DISHES, 2023", // 금요일, 메인 메뉴
            "2023-12-23, MAIN_DISHES, 2023", // 토요일, 메인 메뉴
            "2023-12-18, MAIN_DISHES, 0",   // 월요일, 메인 메뉴 (할인 없음)
            "2023-12-23, DESSERTS, 0",       // 토요일, 디저트 메뉴 (할인 없음)
            "2023-12-23, DRINKS, 0"
    })
    void 주말할인_계산(final String date, final Menu menu, final int expectedDiscountAmount) {
        // Given
        final LocalDate testDate = LocalDate.parse(date);

        // When
        final Benefit benefit = WeekendDiscountEvent.calculateBenefit(testDate, menu);
        final Money result = benefit.getDiscountPrice();

        // Then
        assertThat(result.getAmount()).isEqualTo(expectedDiscountAmount);
    }
}
