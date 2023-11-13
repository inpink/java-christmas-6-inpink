package christmas.domain.event;

import christmas.domain.entity.Money;
import christmas.domain.menu.Item;
import java.util.ArrayList;
import java.util.List;

public class Gifts {
    private final List<Item> gifts;

    private Gifts(List<Item> gifts) {
        this.gifts = gifts;
    }

    public static Gifts create(List<Item> gifts) {
        return new Gifts(gifts);
    }

    public static Gifts createEmpty() {
        return new Gifts(new ArrayList<>());
    }

    public Money calcMoney() {
        return gifts.stream()
                .map(Item::getPrice)
                .reduce(Money.createEmpty(), Money::add);
    }

    public int size() {
        return gifts.size();
    }
}
