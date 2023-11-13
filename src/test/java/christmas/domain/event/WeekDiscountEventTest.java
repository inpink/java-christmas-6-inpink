package christmas.domain.event;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import christmas.domain.event.WeekDiscountEvent;
import christmas.domain.entity.Money;
import christmas.domain.menu.Menu;

class WeekDiscountEventTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-18, DESSERTS, 2023", // 월요일, 디저트 메뉴
            "2023-12-19, DESSERTS, 2023", // 화요일, 디저트 메뉴
            "2023-12-20, DESSERTS, 2023", // 수요일, 디저트 메뉴
            "2023-12-21, DESSERTS, 2023", // 목요일, 디저트 메뉴
            "2023-12-22, MAIN_DISHES, 2023", // 금요일, 메인 메뉴
            "2023-12-23, MAIN_DISHES, 2023", // 토요일, 메인 메뉴
            "2023-12-18, MAIN_DISHES, 0",   // 월요일, 메인 메뉴 (할인 없음)
            "2023-12-23, DESSERTS, 0",       // 토요일, 디저트 메뉴 (할인 없음)
            "2023-12-23, DRINKS, 0"
    })
    void 평일할인_주말할인_계산(String date, Menu menu, int expectedDiscountAmount) {
        // Given
        LocalDate testDate = LocalDate.parse(date);

        // When
        Benefit benefit = WeekDiscountEvent.calculateBenefit(testDate, menu);
        final Money result = benefit.getDiscountPrice();

        // Then
        assertThat(result.getAmount()).isEqualTo(expectedDiscountAmount);
    }
}
