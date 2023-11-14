package christmas.domain.dto;

import christmas.domain.menu.Item;
import java.util.HashMap;
import java.util.List;

public class OrderItemsDto {

    private final HashMap<String, Integer> items;
    private final int priceBeforeDiscount;

    public OrderItemsDto(final HashMap<String, Integer> items, final int priceBeforeDiscount) {
        this.items = items;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public HashMap<String, Integer> getItems() {
        return items;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }
}
