package christmas.domain.menu;

import christmas.domain.money.ItemPrice;

public enum Appetizer implements Item {

    MUSHROOM_SOUP("양송이수프",6_000),
    TAPAS("타파스",5_500),
    CAESAR_SALAD("시저샐러드",8_000);

    private final String name;
    private final ItemPrice price;

    Appetizer(final String name, final int price) {
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
