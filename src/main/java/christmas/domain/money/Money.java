package christmas.domain.money;

import static christmas.messages.ErrorMessages.INVALID_DIVISION_BY_ZERO_MESSAGE;

import christmas.util.ExceptionUtil;
import christmas.validation.IntegerValidator;

public abstract class Money<T extends Money<T>> {

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    protected abstract T create(final int amount);

    public int getAmount() {
        return amount;
    }

    protected void validateAmount(int amount) {
        IntegerValidator.validateNotNegative(amount);
    }
}
