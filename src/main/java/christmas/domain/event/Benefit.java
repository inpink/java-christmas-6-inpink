package christmas.domain.event;

import christmas.domain.entity.Money;
import christmas.domain.menu.Item;
import java.util.List;

public class Benefit {

    private final Money discountPrice;
    private final Gifts gifts;

    private Benefit(final Money discountPrice, final Gifts gifts) {
        this.discountPrice = discountPrice;
        this.gifts = gifts;
    }

    public static Benefit createEmpty() {
        return new Benefit(Money.createEmpty(), Gifts.createEmpty());
    }

    public static Benefit create(final Money discountPrice) {
        return new Benefit(discountPrice, Gifts.createEmpty());
    }

    public static Benefit create(final Gifts gifts) {
        return new Benefit(gifts.calcMoney(), gifts);
    }

    public List<Item> getGiftsByList() {
        return gifts.getGifts();
    }

    public Money getDiscountPrice() {
        return discountPrice;
    }

    public Money getGiftsPrice() {
        return gifts.calcMoney();
    }

    public boolean isNull() {
        return discountPrice.isSmallerOrSameThan(0) && gifts.isNull();
    }
}
