package christmas.domain.event;

import christmas.domain.entity.Money;

public class Benefit {

    private final Money discountPrice;
    private final Gifts gifts;

    public Benefit(Money discountPrice) {
        this.discountPrice = discountPrice;
        this.gifts = null;
    }


    public Money getDiscountPrice() {
        return discountPrice;
    }

    public Gifts getGifts() {
        return gifts;
    }

    public Benefit(Gifts gifts) {
        this.discountPrice = gifts.calcMoney();
        this.gifts = gifts;
    }

    public Benefit(final Money discountPrice, final Gifts gifts) {
        this.discountPrice = discountPrice;
        this.gifts = gifts;
    }

    public Benefit() {
        this.discountPrice = null;
        this.gifts = null;
    }

    public static Benefit createEmpty() {
        return new Benefit(Money.createEmpty(), null);
    }

    public boolean isNull() {
       /* if (할인금액.isSmallerThat(0) && 증정상품.isNull()) {
            return true;
        }*/
        return false;
    }

}
