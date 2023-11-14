package christmas.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class OrderItemsDto {

    private final Map<String, Integer> items;
    private final int priceBeforeDiscount;

    public OrderItemsDto(final Map<String, Integer> items, final int priceBeforeDiscount) {
        this.items = items;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }
}
