package christmas.domain.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;
import static christmas.domain.entity.WeekType.WEEKDAY;
import static christmas.domain.entity.WeekType.WEEKEND;
import static christmas.domain.menu.Menu.DESSERTS;
import static christmas.domain.menu.Menu.MAIN_DISHES;

import christmas.domain.entity.Money;
import christmas.domain.entity.WeekType;
import christmas.domain.menu.Menu;
import java.time.LocalDate;
import java.util.Arrays;

public enum WeekDiscountEvent { //YES or NO 판단하는 용도만 (계산기라기보단 판단)
    //판단용도(상수라기보단 비지니스 로직에 가까움)
    //enum의 장점 : 평일 할인을 관리하는 것을 이 클래스 내부에서 짧은 코드로 관리할 수 있음. 가독성 향상, 응집도 향상.
    //평일 할인은 다른 비지니스 로직에 의존하는 것이 아님. 그렇기에 더더욱 enum화에서 장점을 가짐
    //중앙집중화된 로직,  일관된 인터페이스(다양한 할인 조건을 쉽게 추가하거나 수정할 수 있다.), 타입 안전성이 보장
    //평일 할인이면서 주말 할인인 경우는 논리적으로 없고, 중복 로직이 많아 하나로 합침

    WEEKDAY_DISCOUNT_CONDITIONS(
            WEEKDAY, DESSERTS, 1, 31),
    WEEKEND_DISCOUNT_CONDITIONS(
            WEEKEND, MAIN_DISHES, 1, 31);

    private final WeekType validWeekType;
    private final Menu validMenu;
    private final LocalDate startDate;
    private final LocalDate endDate;

    WeekDiscountEvent(final WeekType validWeekType, final Menu validMenu,
                      final int startDate, final int endDate) {
        this.validWeekType = validWeekType;
        this.validMenu = validMenu;
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date, final Menu menu) {
        if (!isMeetingConditions(date, menu)) {
            return Benefit.createEmpty();
        }
        return new Benefit(calcDiscountPrice());
    }

    private static boolean isMeetingConditions(final LocalDate date, final Menu menu) {
        WeekType weekType = WeekType.findWeekType(date);

        return Arrays.stream(WeekDiscountEvent.values())
                .anyMatch(discount -> discount.validWeekType == weekType &&
                        discount.validMenu == menu &&
                        !date.isBefore(discount.startDate) &&
                        !date.isAfter(discount.endDate));
    }

    private static Money calcDiscountPrice() {
        return DiscountAmount.STANDARD.amount;
    }

    protected enum DiscountAmount {
        STANDARD(2023);

        private final Money amount;

        DiscountAmount(int amount) {
            this.amount = Money.create(amount);
        }
    }

}
