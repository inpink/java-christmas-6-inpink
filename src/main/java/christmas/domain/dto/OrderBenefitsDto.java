package christmas.domain.dto;

import java.util.Map;

public class OrderBenefitsDto {

    private final Map<String, Integer> gifts;
    private final Map<String, Integer> discounts;
    private final int priceBeforeDiscount;
    private final int trickeryDiscount;

    public OrderBenefitsDto(final Map<String, Integer> gifts,
                            final Map<String, Integer> discounts,
                            final int priceBeforeDiscount,
                            int trickeryDiscount) {
        this.gifts = gifts;
        this.discounts = discounts;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.trickeryDiscount = trickeryDiscount;
    }

    public Map<String, Integer> getGifts() {
        return gifts;
    }

    public Map<String, Integer> getDiscounts() {
        return discounts;
    }

    public int getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public int getTrickeryDiscount() {
        return trickeryDiscount;
    }
}
