package christmas.domain.entity.menu;

import christmas.domain.entity.Money;

public enum Drink implements Item {
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인",60_000),
    Champagne("샴페인", 25_000);

    private final String name;
    private final Money price;

    Drink(final String name, final int price) {
        this.name = name;
        this.price = Money.create(price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Money getPrice() {
        return price;
    }
}