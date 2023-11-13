package christmas.domain.event;

import christmas.domain.entity.Money;

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

    public Gifts getGifts() {
        return gifts;
    }

    public Money getDiscountPrice() {
        return discountPrice;
    }

    public boolean isNull() {
       /* if (할인금액.isSmallerThat(0) && 증정상품.isNull()) {
            return true;
        }*/
        return false;
    }
}
