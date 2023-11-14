package christmas.domain.entity.menu;

import static christmas.constants.IntegerConstants.MIN_ORDER_ITEM_COUNT;

import christmas.validation.IntegerValidator;

public class ItemCount {

    private final int count;

    private ItemCount(final int count) {
        validate(count);
        this.count = count;
    }

    public static ItemCount create(final String input) {
        IntegerValidator.validateInteger(input);
        return new ItemCount(Integer.parseInt(input));
    }

    private void validate(final int count) {
        IntegerValidator.validateNotNegative(count);
        IntegerValidator.validateNotSmaller(count, MIN_ORDER_ITEM_COUNT.getValue());
    }

    public int getCount() {
        return this.count;
    }
}
