package christmas.domain.event;


import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;
import static christmas.domain.event.GiftEvent.GiftItem.CHAMPAGNE;
import static christmas.domain.menu.Drink.Champagne;

import christmas.domain.entity.Money;
import christmas.domain.menu.Item;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public enum GiftEvent {

    GIFT_EVENT_CONDITIONS(12_000, 1, 25);

    private final Money minimumMoney;
    private final LocalDate startDate;
    private final LocalDate endDate;


    private GiftEvent(final int minimumMoney, final int startDate, final int endDate) {
        this.minimumMoney = Money.create(minimumMoney);
        this.startDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), startDate);
        this.endDate = LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), endDate);
    }

    public static Benefit calculateBenefit(final LocalDate date, final Money totalOrderPrice) {
        if (!isMeetingConditions(date, totalOrderPrice)) {
            return Benefit.createEmpty();
        }
        return Benefit.create(createGifts());
    }

    private static boolean isMeetingConditions(final LocalDate date, final Money totalOrderPrice) {
        return totalOrderPrice.isBiggerOrSameThan(GIFT_EVENT_CONDITIONS.minimumMoney) &&
                !date.isBefore(GIFT_EVENT_CONDITIONS.startDate) &&
                !date.isAfter(GIFT_EVENT_CONDITIONS.endDate);
    }

    private static Gifts createGifts() {
        final List<Item> giftItems = Arrays.stream(GiftItem.values())
                .map(GiftItem::getItem)
                .toList();

        return Gifts.create(giftItems);
    }


    protected enum GiftItem {
        CHAMPAGNE(Champagne);

        private Item item;

        GiftItem(final Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }
    }
}
