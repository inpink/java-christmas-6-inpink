package christmas.domain.event;

import christmas.domain.menu.Item;
import christmas.domain.money.DiscountPrice;
import java.util.ArrayList;
import java.util.List;

public class Gifts {
    private final List<Item> gifts;

    private Gifts(List<Item> gifts) {
        this.gifts = gifts;
    }

    public static Gifts createEmpty() {
        return new Gifts(new ArrayList<>());
    }

    public DiscountPrice calcMoney() {
        ItemPrice total = gifts.stream()
                .mapToInt(Item::getPrice)
                .sum();
        return new DiscountPrice(total);
    }
}
