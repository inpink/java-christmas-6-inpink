package christmas.domain.event;

import static christmas.messages.ErrorMessages.INVALID_BADGE_PRICE;

import christmas.domain.money.DiscountPrice;
import christmas.util.ExceptionUtil;
import java.util.Arrays;

public enum Badge {

    NO("없음", 0, 4_999),
    STAR("별", 5_000, 9_999),
    TREE("트리", 10_000, 19_999),
    SANTA("산타", 20_000, Integer.MAX_VALUE);

    private final String name;
    private final DiscountPrice minBenefitsPrice;
    private final DiscountPrice maxBenefitsPrice;

    Badge(final String name, final int minBenefitsPrice, final int maxBenefitsPrice) {
        this.name = name;
        this.minBenefitsPrice = new DiscountPrice(minBenefitsPrice);
        this.maxBenefitsPrice = new DiscountPrice(maxBenefitsPrice);
    }

    public static Badge getBadgeByPrice(final DiscountPrice price) {
        return Arrays.stream(Badge.values())
                .filter(badge -> price.isInRange(badge.minBenefitsPrice, badge.maxBenefitsPrice))
                .findFirst()
                .orElseThrow(()
                        -> ExceptionUtil.returnInvalidValueException(INVALID_BADGE_PRICE.getMessage()));
    }
}
