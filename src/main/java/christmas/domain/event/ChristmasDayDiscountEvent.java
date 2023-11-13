package christmas.domain.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;
import static christmas.domain.event.ChristmasDayDiscountEvent.DiscountAmount.DAILY_SURCHARGE;
import static christmas.domain.event.ChristmasDayDiscountEvent.DiscountAmount.STANDARD;
import static java.time.temporal.ChronoUnit.DAYS;

import christmas.domain.money.DiscountPrice;
import java.time.LocalDate;

public enum ChristmasDayDiscountEvent { //정해진 조건을 상수로 관리한다는 느낌을 더욱 주고 싱글톤의 장점도
    DISCOUNT_CONDITIONS(1, 25);

    private final LocalDate startDate;
    private final LocalDate endDate;

    ChristmasDayDiscountEvent(final int startDate, final int endDate) {
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date) {
        if (!isMeetingConditions(date)) {
            return Benefit.createEmpty();
        }
        return new Benefit(calcDiscountPrice(date));
    }

    private static boolean isMeetingConditions(final LocalDate date) {
        return !(date.isBefore(DISCOUNT_CONDITIONS.startDate)
                || date.isAfter(DISCOUNT_CONDITIONS.endDate));
    }

    private static DiscountPrice calcDiscountPrice(final LocalDate date) {
        final DiscountPrice dailySurcharge = calculateDailySurcharge(date);
        return (DiscountPrice) dailySurcharge.add(STANDARD.amount);
    }

    private static DiscountPrice calculateDailySurcharge(final LocalDate date) {
        final int daysSinceStart = (int) DAYS.between(DISCOUNT_CONDITIONS.startDate, date);
        final DiscountPrice oneDaySurcharge = DAILY_SURCHARGE.amount;
        return (DiscountPrice) oneDaySurcharge.multiply(daysSinceStart);
    }

    enum DiscountAmount {

        STANDARD(1000),
        DAILY_SURCHARGE(100);

        private final DiscountPrice amount;

        DiscountAmount(final int amount) {
            this.amount = new DiscountPrice(amount);
        }
    }
}
