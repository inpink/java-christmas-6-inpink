package christmas.domain.menu;

import christmas.domain.money.ItemPrice;

public enum Dessert implements Item {
    CHOCOLATE_CAKE("초코케이크",15_000),
    ICECREAM("아이스크림",5_000);

    private final String name;
    private final ItemPrice price;

    Dessert(final String name, final int price) {
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
