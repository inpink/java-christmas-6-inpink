package christmas.domain.money;

import static christmas.messages.ErrorMessages.INVALID_DIVISION_BY_ZERO;
import static christmas.messages.ErrorMessages.INVALID_NEGATIVE_VALUE;

import christmas.util.ExceptionUtil;
import christmas.validation.IntegerValidator;

public abstract class Money<T extends Money<T>> {

    private final int amount;

    public Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    protected abstract T create(final int amount);

    public int getAmount() {
        return amount;
    }

    public boolean isBiggerOrSameThan(final int amount) {
        return this.amount >= amount;
    }

    public boolean isSmallerThan(final int amount) {
        return this.amount < amount;
    }

    public boolean isBiggerOrSameThan(final Money<?> other) {
        return this.amount >= other.amount;
    }

    public boolean isSmallerOrSameThan(final Money<?> other) {
        return this.amount <= other.amount;
    }

    public boolean isInRange(final Money<?> minPrice, final Money<?> maxPrice) {
        return isBiggerOrSameThan(minPrice) && isSmallerOrSameThan(maxPrice);
    }

    public T add(final Money<?> other) {
        return create(this.amount + other.amount);
    }

    public T subtract(final Money<?> other) {
        return create(this.amount - other.amount);
    }

    public T multiply(final Money<?> other) {
        return create(this.amount * other.amount);
    }

    public T multiply(final int value) {
        return create(this.amount * value);
    }
    public T divide(final Money<?> other) {
        if (other.amount == 0) {
            ExceptionUtil.throwInvalidValueException(INVALID_DIVISION_BY_ZERO.getMessage());
        }
        return create(this.amount / other.amount);
    }

    public T calculateRemainder(final Money<?> other) {
        if (other.amount == 0) {
            ExceptionUtil.throwInvalidValueException(INVALID_DIVISION_BY_ZERO.getMessage());
        }
        return create(this.amount % other.amount);
    }

    private void validate(int amount) {
        IntegerValidator.validateNotNegative(amount, INVALID_NEGATIVE_VALUE.getMessage());
    }

}
