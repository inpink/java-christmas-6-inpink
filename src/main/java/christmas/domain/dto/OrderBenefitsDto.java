package christmas.domain.dto;

import christmas.domain.event.Event;
import christmas.domain.menu.Item;
import java.util.EnumMap;
import java.util.HashMap;

public class OrderBenefitsDto {

    private final HashMap<Item, Integer> gifts;
    private final EnumMap<Event, Integer> discounts;
    private final int priceBeforeDiscount;

    public OrderBenefitsDto(final HashMap<Item, Integer> gifts,
                            final EnumMap<Event, Integer> discounts,
                            final int priceBeforeDiscount) {
        this.gifts = gifts;
        this.discounts = discounts;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public HashMap<Item, Integer> getGifts() {
        return gifts;
    }

    public EnumMap<Event, Integer> getDiscounts() {
        return discounts;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }
}
