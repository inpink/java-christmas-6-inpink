package christmas.domain.event;

import java.util.Arrays;

public enum Badge {

    STAR("별", 5_000, 9_999),
    TREE("트리", 10_000, 19_999),
    SANTA("산타", 20_000, Integer.MAX_VALUE);

    private final String name;
    private final int minBenefitsPrice;
    private final int maxBenefitsPrice;

    Badge(final String name, final int minBenefitsPrice, final int maxBenefitsPrice) {
        this.name = name;
        this.minBenefitsPrice = minBenefitsPrice;
        this.maxBenefitsPrice = maxBenefitsPrice;
    }

    public static Badge getBadgeByPrice(final int price) {
        return Arrays.stream(Badge.values())
                .filter(badge -> badge.isPriceInRange(price))
                .findFirst()
                .orElse(null);
    }

    private boolean isPriceInRange(final int price) {
        return price >= minBenefitsPrice && price <= maxBenefitsPrice;
    }

}
