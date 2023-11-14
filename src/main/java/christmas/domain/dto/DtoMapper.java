package christmas.domain.dto;

import christmas.domain.event.Event;
import christmas.domain.menu.Item;
import java.util.EnumMap;
import java.util.HashMap;

public class DtoMapper {

    private DtoMapper() {

    }

    public static MemberBadgeDto toMemberBadgeDto(final String badge) {
        return new MemberBadgeDto(badge);
    }

    public static OrderItemsDto toOrderItemsDto(final HashMap<Item, Integer> items,
                                                final int priceBeforeDiscount) {
        return new OrderItemsDto(items, priceBeforeDiscount);
    }

    public static OrderBenefitsDto toOrderBenefitsDto(final HashMap<Item, Integer> gifts,
                                                      final EnumMap<Event, Integer> discounts,
                                                      final int priceBeforeDiscount) {
        return new OrderBenefitsDto(gifts, discounts, priceBeforeDiscount);
    }
}
