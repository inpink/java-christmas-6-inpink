package christmas.domain.entity;

import static christmas.messages.ErrorMessages.INVALID_DIVISION_BY_ZERO;
import static christmas.messages.ErrorMessages.INVALID_NEGATIVE_VALUE;

import christmas.util.ExceptionUtil;
import christmas.validation.IntegerValidator;

public final class Money {

    private final int amount;

    private Money(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    public static Money create(final int amount) {
        return new Money(amount);
    }

    public static Money createEmpty() {
        return new Money(0);
    }

    public int getAmount() {
        return amount;
    }

    public boolean isBiggerOrSameThan(final int amount) {
        return this.amount >= amount;
    }

    public boolean isSmallerThan(final int amount) {
        return this.amount < amount;
    }

    public boolean isBiggerOrSameThan(final Money other) {
        return this.amount >= other.amount;
    }

    public boolean isSmallerOrSameThan(final Money other) {
        return this.amount <= other.amount;
    }

    public boolean isSmallerOrSameThan(final int amount) {
        return this.amount <= amount;
    }

    public boolean isInRange(final Money minPrice, final Money maxPrice) {
        return isBiggerOrSameThan(minPrice) && isSmallerOrSameThan(maxPrice);
    }

    public Money add(final Money other) {
        return create(this.amount + other.amount);
    }

    public Money subtract(final Money other) {
        return create(this.amount - other.amount);
    }

    public Money multiply(final Money other) {
        return create(this.amount * other.amount);
    }

    public Money multiply(final int value) {
        return create(this.amount * value);
    }

    public Money divide(final Money other) {
        if (other.amount == 0) {
            ExceptionUtil.throwInvalidValueException(INVALID_DIVISION_BY_ZERO.getMessage());
        }
        return create(this.amount / other.amount);
    }

    public Money calculateRemainder(final Money other) {
        if (other.amount == 0) {
            ExceptionUtil.throwInvalidValueException(INVALID_DIVISION_BY_ZERO.getMessage());
        }
        return create(this.amount % other.amount);
    }

    private void validate(final int amount) {
        IntegerValidator.validateNotNegative(amount, INVALID_NEGATIVE_VALUE.getMessage());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Money other = (Money) o;
        return this.amount == other.amount;
    }
}
