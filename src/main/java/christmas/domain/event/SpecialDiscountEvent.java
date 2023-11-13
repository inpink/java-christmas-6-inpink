package christmas.domain.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import christmas.domain.entity.Money;
import java.time.LocalDate;

public enum SpecialDiscountEvent {

    SPECIAL_DISCOUNT_CONDITIONS(
            Starred.YES, 1,31);

    private final Starred starred;
    private final LocalDate startDate;
    private final LocalDate endDate;

    SpecialDiscountEvent(final Starred starred, final int startDate, final int endDate) {
        this.starred = starred;
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date) {
        if (!isMeetingConditions(date)) {
            return Benefit.createEmpty();
        }
        return new Benefit(calcDiscountPrice());
    }

    private static boolean isMeetingConditions(final LocalDate date) {
        return Starred.YES == Starred.findStarred(date) &&
                !date.isBefore(SPECIAL_DISCOUNT_CONDITIONS.startDate) &&
                !date.isAfter(SPECIAL_DISCOUNT_CONDITIONS.endDate);
    }

    private static Money calcDiscountPrice() {
        return DiscountAmount.STANDARD.amount;
    }

    protected enum DiscountAmount {
        STANDARD(1000);

        private final Money amount;

        DiscountAmount(final int amount) {
            this.amount = Money.create(amount);
        }
    }
}
