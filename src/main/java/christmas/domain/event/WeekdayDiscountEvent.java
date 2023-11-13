package christmas.domain.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;
import static christmas.domain.entity.WeekType.WEEKDAY;
import static christmas.domain.event.WeekdayDiscountEvent.DiscountAmount.STANDARD;
import static christmas.domain.menu.Menu.DESSERTS;

import christmas.domain.entity.Money;
import christmas.domain.entity.WeekType;
import christmas.domain.menu.Menu;
import java.time.DayOfWeek;
import java.time.LocalDate;

public enum WeekdayDiscountEvent {
    DISCOUNT_CONDITIONS(WEEKDAY, DESSERTS, 1, 31);

    private final WeekType validWeekType;
    private final Menu validMenu;
    private final LocalDate startDate;
    private final LocalDate endDate;

    WeekdayDiscountEvent(final WeekType validWeekType, final Menu validMenu, final int startDate, final int endDate) {
        this.validWeekType = validWeekType;
        this.validMenu = validMenu;
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date, final Menu menu) {
        if (isApplicable(date, menu)) {
            return Benefit.create(calcDiscountPrice());
        }
        return Benefit.createEmpty();
    }

    private static boolean isApplicable(final LocalDate date, final Menu menu) {
        return isDateInValidWeekType(date) &&
                DISCOUNT_CONDITIONS.validMenu == menu &&
                !date.isBefore(DISCOUNT_CONDITIONS.startDate) &&
                !date.isAfter(DISCOUNT_CONDITIONS.endDate);
    }

    private static boolean isDateInValidWeekType(final LocalDate date) {
        final DayOfWeek dayOfWeek = date.getDayOfWeek();
        final WeekType currentWeekType = DISCOUNT_CONDITIONS.validWeekType;

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
