package christmas.domain.menu;

import christmas.domain.entity.Money;

public interface Item {

    String getName();

    Money getPrice();

    default boolean hasItem(String name) {
        return getName().equalsIgnoreCase(name);
    }

}
