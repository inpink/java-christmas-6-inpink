package christmas.domain.dto;

import christmas.domain.event.Event;
import christmas.domain.menu.Item;
import java.util.EnumMap;
import java.util.HashMap;

public class OrderBenefitsDto {

    private final HashMap<String, Integer> gifts;
    private final HashMap<String, Integer> discounts;
    private final int priceBeforeDiscount;

    public OrderBenefitsDto(final HashMap<String, Integer> gifts,
                            final HashMap<String, Integer> discounts,
                            final int priceBeforeDiscount) {
        this.gifts = gifts;
        this.discounts = discounts;
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public HashMap<String, Integer> getGifts() {
        return gifts;
    }

    public HashMap<String, Integer> getDiscounts() {
        return discounts;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }
}
