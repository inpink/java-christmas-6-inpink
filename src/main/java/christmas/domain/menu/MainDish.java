package christmas.domain.menu;

import christmas.domain.money.ItemPrice;

public enum MainDish implements Item {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String name;
    private final ItemPrice price;

    MainDish(final String name, final int price) {
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
