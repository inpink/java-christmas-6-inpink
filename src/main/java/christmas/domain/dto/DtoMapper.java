package christmas.domain.dto;

import java.util.Map;

public class DtoMapper {

    private DtoMapper() {

    }

    public static MemberBadgeDto toMemberBadgeDto(final String badge) {
        return new MemberBadgeDto(badge);
    }

    public static OrderItemsDto toOrderItemsDto(final Map<String, Integer> items,
                                                final int priceBeforeDiscount) {
        return new OrderItemsDto(items, priceBeforeDiscount);
    }

    public static OrderBenefitsDto toOrderBenefitsDto(final Map<String, Integer> gifts,
                                                      final Map<String, Integer> discounts,
                                                      final int priceBeforeDiscount,
                                                      final int trickeryDiscount) {
        return new OrderBenefitsDto(gifts, discounts, priceBeforeDiscount, trickeryDiscount);
    }
}
