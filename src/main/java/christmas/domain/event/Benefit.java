package christmas.domain.event;

import christmas.domain.money.DiscountPrice;

public class Benefit {


    private final DiscountPrice discountPrice;
    private final Gifts gifts;

    public Benefit(DiscountPrice discountPrice) {
        this.discountPrice=discountPrice;
        this.gifts = null;
    }


    public DiscountPrice getDiscountPrice() {
        return discountPrice;
    }

    public Gifts getGifts() {
        return gifts;
    }

    public Benefit(Gifts gifts) {
        this.discountPrice = gifts.calcMoney();
        this.gifts = gifts;
    }

    public Benefit(DiscountPrice discountPrice, Gifts gifts) {
        this.discountPrice = discountPrice;
        this.gifts = gifts;
    }

    public Benefit() {
        this.discountPrice=null;
        this.gifts=null;
    }

    public static Benefit createEmpty() {
        return new Benefit(new DiscountPrice(0), null);
    }

    public boolean isNull() {
       /* if (할인금액.isSmallerThat(0) && 증정상품.isNull()) {
            return true;
        }*/
        return false;
    }

}
