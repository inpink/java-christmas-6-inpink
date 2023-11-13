package christmas.domain.menu;

import christmas.domain.entity.Money;

public enum Dessert implements Item {
    CHOCOLATE_CAKE("초코케이크",15_000),
    ICECREAM("아이스크림",5_000);

    private final String name;
    private final Money price;

    Dessert(final String name, final int price) {
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
