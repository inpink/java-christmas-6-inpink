package christmas.domain.dto;

import java.util.EnumMap;
import java.util.HashMap;

public class DtoMapper {

    private DtoMapper() {

    }

    public static MemberBadgeDto toMemberBadgeDto(final String badge) {
        return new MemberBadgeDto(badge);
    }

    public static OrderItemsDto toOrderItemsDto(final HashMap<String, Integer> items,
                                                final int priceBeforeDiscount) {
        return new OrderItemsDto(items, priceBeforeDiscount);
    }

    public static OrderBenefitsDto toOrderBenefitsDto(final HashMap<String, Integer> gifts,
                                                      final HashMap<String, Integer> discounts,
                                                      final int priceBeforeDiscount) {
        return new OrderBenefitsDto(gifts, discounts, priceBeforeDiscount);
    }
}
