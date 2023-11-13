package christmas.domain.money;

public class DiscountPrice extends Money {

    public DiscountPrice(final int amount) {
        super(amount);
    }

    @Override
    protected DiscountPrice create(final int amount) {
        return new DiscountPrice(amount);
    }

}
