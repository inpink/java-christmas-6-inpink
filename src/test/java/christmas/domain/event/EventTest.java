package christmas.domain.event;

import christmas.domain.menu.Items;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static christmas.domain.event.Event.CHRISTMAS_DAY_DISCOUNT;
import static christmas.domain.event.Event.GIFT_EVENT;
import static christmas.domain.event.Event.SPECIAL_DISCOUNT;
import static christmas.domain.event.Event.WEEKDAY_DISCOUNT;
import static christmas.domain.event.Event.WEEKEND_DISCOUNT;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.stream.Stream;
import christmas.domain.entity.Money;

class EventTest {

    @ParameterizedTest
    @MethodSource("provideTestCases")
    public void 모든_해당되는_이벤트_찾기(final LocalDate date,
                               final Items items,
                               final Money totalOrderPrice,
                               final EnumSet<Event> expectedEvents,
                               final int discountAmount) {
        // When
        final Benefits calculatedBenefits = Event.findBenefits(date, items, totalOrderPrice);
        final Money calculatedTotalDiscount = calculatedBenefits.calcTotalDiscount();

        // Then
        assertThat(calculatedBenefits.findAllEvents())
                .containsExactlyInAnyOrderElementsOf(expectedEvents);
        assertThat(calculatedTotalDiscount.getAmount()).isEqualTo(discountAmount);
    }

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                // 10000원 미만은 혜택 없음
                Arguments.of(LocalDate.of(2023, 12, 1),
                        Items.create("크리스마스파스타-1, 제로콜라-3"),
                        Money.create(9999),
                        EnumSet.noneOf(Event.class),
                        0),

                // 크리스마스 디데이 할인 테스트 케이스
                Arguments.of(LocalDate.of(2023, 12, 21),
                        Items.create("티본스테이크-1"),
                        Money.create(50000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT),
                        3000),

                // 평일 할인 테스트 케이스 (12월 27일은 수요일)
                Arguments.of(LocalDate.of(2023, 12, 27),
                        Items.create("초코케이크-1"),
                        Money.create(10000),
                        EnumSet.of(WEEKDAY_DISCOUNT),
                        2023),

                // 주말 할인 테스트 케이스 (12월 30일은 토요일)
                Arguments.of(LocalDate.of(2023, 12, 30),
                        Items.create("티본스테이크-1"),
                        Money.create(10000),
                        EnumSet.of(WEEKEND_DISCOUNT),
                        2023),

                // 특별 할인 테스트 케이스
                Arguments.of(LocalDate.of(2023, 12, 31),
                        Items.create("티본스테이크-1"),
                        Money.create(50000),
                        EnumSet.of(SPECIAL_DISCOUNT),
                        1000),

                // 증정 이벤트 테스트 케이스 (총주문 금액 120,000원 이상)
                Arguments.of(LocalDate.of(2023, 12, 28),
                        Items.create("티본스테이크-1"),
                        Money.create(120_000),
                        EnumSet.of(GIFT_EVENT),
                        25_000),

                // 이벤트 날짜가 아님
                Arguments.of(LocalDate.of(2023, 11, 30),
                        Items.create("티본스테이크-1"),
                        Money.create(50000),
                        EnumSet.noneOf(Event.class),
                        0),

                // 통합 테스트
                Arguments.of(LocalDate.of(2023, 12, 1),
                        Items.create("티본스테이크-1"),
                        Money.create(50000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKEND_DISCOUNT),
                        1000 + 2023),

                Arguments.of(LocalDate.of(2023, 12, 4),
                        Items.create("초코케이크-1"),
                        Money.create(120_000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKDAY_DISCOUNT,
                                GIFT_EVENT),
                        1300 + 2023 + 25000),

                Arguments.of(LocalDate.of(2023, 12, 25),
                        Items.create("초코케이크-1"),
                        Money.create(220_000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKDAY_DISCOUNT,
                                SPECIAL_DISCOUNT,
                                GIFT_EVENT),
                        3400 + 2023 + 1000 + 25000),

                // 주문 상품이 여러개
                Arguments.of(LocalDate.of(2023, 12, 1),
                        Items.create("티본스테이크-3"),
                        Money.create(50000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKEND_DISCOUNT),
                        1000 + 2023 * 3),

                Arguments.of(LocalDate.of(2023, 12, 4),
                        Items.create("초코케이크-1,티본스테이크-3"),
                        Money.create(120_000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKDAY_DISCOUNT,
                                GIFT_EVENT),
                        1300 + 2023 + 25000),

                Arguments.of(LocalDate.of(2023, 12, 25),
                        Items.create("초코케이크-5, 티본스테이크-3"),
                        Money.create(220_000),
                        EnumSet.of(CHRISTMAS_DAY_DISCOUNT,
                                WEEKDAY_DISCOUNT,
                                SPECIAL_DISCOUNT,
                                GIFT_EVENT),
                        3400 + 2023 * 5 + 1000 + 25000)
        );
    }

}
