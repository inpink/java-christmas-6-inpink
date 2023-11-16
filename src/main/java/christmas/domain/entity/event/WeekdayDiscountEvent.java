package christmas.domain.entity.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;
import static christmas.domain.entity.WeekType.WEEKDAY;
import static christmas.domain.entity.event.WeekdayDiscountEvent.DiscountAmount.STANDARD;
import static christmas.domain.entity.menu.Menu.DESSERTS;

import christmas.domain.entity.Money;
import christmas.domain.entity.WeekType;
import christmas.domain.entity.menu.Item;
import christmas.domain.entity.menu.ItemCount;
import christmas.domain.entity.menu.Items;
import christmas.domain.entity.menu.Menu;
import christmas.util.LocalDateUtil;
import java.time.DayOfWeek;
import java.time.LocalDate;

//YES or NO 판단하는 용도만 (계산기라기보단 판단)
//판단용도(상수라기보단 비지니스 로직에 가까움)
//enum의 장점 : 평일 할인을 관리하는 것을 이 클래스 내부에서 짧은 코드로 관리할 수 있음. 가독성 향상, 응집도 향상.
//평일 할인은 다른 비지니스 로직에 의존하는 것이 아님. 그렇기에 더더욱 enum화에서 장점을 가짐
//중앙집중화된 로직,  일관된 인터페이스(다양한 할인 조건을 쉽게 추가하거나 수정할 수 있다.), 타입 안전성이 보장
//평일 할인이면서 주말 할인인 경우는 논리적으로 없고, 중복 로직이 많아 하나로 합침
public enum WeekdayDiscountEvent {
    WEEKDAY_DISCOUNT_CONDITIONS(WEEKDAY, DESSERTS, 1, 31);

    private final WeekType validWeekType;
    private final Menu validMenu;
    private final LocalDate startDate;
    private final LocalDate endDate;

    WeekdayDiscountEvent(final WeekType validWeekType,
                         final Menu validMenu,
                         final int startDate,
                         final int endDate) {
        this.validWeekType = validWeekType;
        this.validMenu = validMenu;
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date, final Items items) {
        final Money totalDiscount = items.toEntrySet()
                .stream()
                .map(entry -> {
                    final Item item = entry.getKey();
                    final ItemCount itemCount = entry.getValue();
                    final Menu menu = Menu.findMenu(item);

                    if (!isApplicable(date, menu)) {
                        return Money.create(0);
                    }
                    return calcDiscountPrice().multiply(itemCount.getCount());
                })
                .reduce(Money.create(0), Money::add);

        return Benefit.create(totalDiscount);
    }

    private static boolean isApplicable(final LocalDate date, final Menu menu) {
        return isDateInValidWeekType(date) &&
                WEEKDAY_DISCOUNT_CONDITIONS.validMenu == menu &&
                LocalDateUtil.isWithinDateRange(
                        date,
                        WEEKDAY_DISCOUNT_CONDITIONS.startDate,
                        WEEKDAY_DISCOUNT_CONDITIONS.endDate);
    }

    private static boolean isDateInValidWeekType(final LocalDate date) {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        final WeekType currentWeekType = WEEKDAY_DISCOUNT_CONDITIONS.validWeekType;

        return currentWeekType.contains(dayOfWeek);
    }

    private static Money calcDiscountPrice() {
        return STANDARD.amount;
    }

    protected enum DiscountAmount {
        STANDARD(2023);

        private final Money amount;

        DiscountAmount(int amount) {
            this.amount = Money.create(amount);
        }
    }
}
