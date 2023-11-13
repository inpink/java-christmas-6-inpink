package christmas.domain.menu;

import christmas.domain.money.ItemPrice;

public enum Drink implements Item {
    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인",60_000),
    Champagne("샴페인", 25_000);

    private final String name;
    private final ItemPrice price;

    Drink(final String name, final int price) {
        this.name = name;
        this.price = new ItemPrice(price);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public ItemPrice getPrice() {
        return price;
    }
}