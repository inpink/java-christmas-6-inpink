package christmas.domain.menu;

public enum Dessert implements Item {
    CHOCOLATE_CAKE("초코케이크",15_000),
    ICECREAM("아이스크림",5_000);

    private final String name;
    private final int price;

    Dessert(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
