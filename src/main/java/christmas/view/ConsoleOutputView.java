package christmas.view;

import static christmas.messages.OutputMessages.BENEFITS_TITLE;
import static christmas.messages.OutputMessages.GIFT_MENU_TITLE;
import static christmas.messages.OutputMessages.ITEM_COUNT;
import static christmas.messages.OutputMessages.KOREAN_WON;
import static christmas.messages.OutputMessages.NOT_EXIST;
import static christmas.messages.OutputMessages.ORDER_MENU_TITLE;
import static christmas.messages.OutputMessages.PREVIEW_TITLE;
import static christmas.messages.OutputMessages.THIS_MONTH_EVENT_BADGE_TITLE;
import static christmas.messages.OutputMessages.TOTAL_BENEFITS_PRICE_TITLE;
import static christmas.messages.OutputMessages.TOTAL_PRICE_AFTER_DISCOUNT_TITLE;
import static christmas.messages.OutputMessages.TOTAL_PRICE_BEFORE_DISCOUNT_TITLE;

import christmas.domain.dto.MemberBadgeDto;
import christmas.domain.dto.OrderBenefitsDto;
import christmas.domain.dto.OrderItemsDto;
import christmas.util.OutputUtil;
import christmas.util.StringUtil;
import java.util.Map;

public class ConsoleOutputView implements OutputView {

    @Override
    public void outputPreviewTitle() {
        outputTitle(PREVIEW_TITLE.getMessage());
        OutputUtil.printEmptyLine();
    }

    @Override
    public void outputOrderItems(final OrderItemsDto orderItemsDto) {
        outputItemsAndCounts(orderItemsDto.getItems());
        outputTotalPriceBeforeDiscount(orderItemsDto.getPriceBeforeDiscount());
    }

    @Override
    public void outputBenefits(OrderBenefitsDto orderBenefitsDto) {
        final Map<String, Integer> gifts = orderBenefitsDto.getGifts();
        final Map<String, Integer> discounts = orderBenefitsDto.getDiscounts();
        final int totalPriceBeforeDiscount = orderBenefitsDto.getPriceBeforeDiscount();
        final int trickeryDiscount = orderBenefitsDto.getTrickeryDiscount();
        final int sumDiscounts = sumDiscounts(discounts);

        outputGifts(gifts);
        outputDiscounts(discounts);
        outputSumOfDiscounts(sumDiscounts);
        outputTotalPriceAfterDiscount(totalPriceBeforeDiscount, sumDiscounts, trickeryDiscount);
    }

    @Override
    public void outputThisMonthBadge(MemberBadgeDto memberBadgeDto) {
        outputTitle(THIS_MONTH_EVENT_BADGE_TITLE.getMessage());

        final String badge = memberBadgeDto.getBadge();
        System.out.println(badge);
    }

    private void outputItemsAndCounts(final Map<String, Integer> itemsAndCounts) {
        outputTitle(ORDER_MENU_TITLE.getMessage());

        for (Map.Entry<String, Integer> entry : itemsAndCounts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + ITEM_COUNT.getMessage());
        }
        OutputUtil.printEmptyLine();
    }

    private void outputTotalPriceBeforeDiscount(final int priceBeforeDiscount) {
        outputTitle(TOTAL_PRICE_BEFORE_DISCOUNT_TITLE.getMessage());
        System.out.println(StringUtil.formatByThousandSeparator(priceBeforeDiscount)
                + KOREAN_WON.getMessage());
        OutputUtil.printEmptyLine();
    }

    private int sumDiscounts(final Map<String, Integer> discounts) {
        return discounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void outputGifts(final Map<String, Integer> gifts) {
        outputTitle(GIFT_MENU_TITLE.getMessage());

        if (gifts.isEmpty()) {
            System.out.println(NOT_EXIST.getMessage());
        }
        for (Map.Entry<String, Integer> entry : gifts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + ITEM_COUNT.getMessage());
        }
        OutputUtil.printEmptyLine();
    }

    private void outputDiscounts(final Map<String, Integer> discounts) {
        outputTitle(BENEFITS_TITLE.getMessage());

        if (discounts.isEmpty()) {
            System.out.println(NOT_EXIST.getMessage());
        }
        for (Map.Entry<String, Integer> discountEntry : discounts.entrySet()) {
            System.out.println(discountEntry.getKey()
                    + StringUtil.formatByThousandSeparator(discountEntry.getValue() * -1)
                    + KOREAN_WON.getMessage());
        }
        OutputUtil.printEmptyLine();
    }

    private void outputSumOfDiscounts(final int sumDiscounts) {
        outputTitle(TOTAL_BENEFITS_PRICE_TITLE.getMessage());
        System.out.println(StringUtil.formatByThousandSeparator(sumDiscounts * -1)
                + KOREAN_WON.getMessage());
        OutputUtil.printEmptyLine();
    }

    private void outputTotalPriceAfterDiscount(final int totalPriceBeforeDiscount,
                                               final int sumDiscounts,
                                               final int trickeryDiscount) {
        outputTitle(TOTAL_PRICE_AFTER_DISCOUNT_TITLE.getMessage());

        final int finalPrice = totalPriceBeforeDiscount
                - sumDiscounts
                + trickeryDiscount;
        System.out.println(StringUtil.formatByThousandSeparator(finalPrice) + KOREAN_WON.getMessage());

        OutputUtil.printEmptyLine();
    }

    private void outputTitle(String title) {
        System.out.println("<" + title + ">");
    }
}
