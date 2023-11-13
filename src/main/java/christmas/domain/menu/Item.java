package christmas.domain.menu;

import christmas.domain.money.ItemPrice;

public interface Item {

    String getName();

    ItemPrice getPrice();

    default boolean hasItem(String name) {
        return getName().equalsIgnoreCase(name);
    }

}
