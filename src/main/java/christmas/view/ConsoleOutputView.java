package christmas.view;

import static christmas.messages.OutputMessages.BENEFITS_TITLE;
import static christmas.messages.OutputMessages.GIFT_MENU_TITLE;
import static christmas.messages.OutputMessages.ORDER_MENU_TITLE;
import static christmas.messages.OutputMessages.PREVIEW_TITLE;
import static christmas.messages.OutputMessages.THIS_MONTH_EVENT_BADGE_TITLE;
import static christmas.messages.OutputMessages.TOTAL_PRICE_AFTER_DISCOUNT_TITLE;
import static christmas.messages.OutputMessages.TOTAL_PRICE_BEFORE_DISCOUNT_TITLE;

import christmas.domain.dto.MemberBadgeDto;
import christmas.domain.dto.OrderBenefitsDto;
import christmas.domain.dto.OrderItemsDto;
import christmas.util.OutputUtil;
import christmas.util.StringUtil;
import java.util.HashMap;
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
        final HashMap<String, Integer> gifts = orderBenefitsDto.getGifts();
        final HashMap<String, Integer> discounts = orderBenefitsDto.getDiscounts();
        final int totalPriceBeforeDiscount = orderBenefitsDto.getPriceBeforeDiscount();
        final int sumDiscounts = sumDiscounts(discounts);
        final int totalPriceAfterDiscount = totalPriceBeforeDiscount - sumDiscounts;

        outputGifts(gifts);
        outputDiscounts(discounts);
        outputTotalPriceAfterDiscount(totalPriceAfterDiscount);
    }

    @Override
    public void outputThisMonthBadge(MemberBadgeDto memberBadgeDto) {
        outputTitle(THIS_MONTH_EVENT_BADGE_TITLE.getMessage());

        final String badge = memberBadgeDto.getBadge();
        System.out.println(badge);
    }

    private void outputItemsAndCounts(final HashMap<String, Integer> itemsAndCounts) {
        outputTitle(ORDER_MENU_TITLE.getMessage());

        for (Map.Entry<String, Integer> entry : itemsAndCounts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
        OutputUtil.printEmptyLine();
    }

    private void outputTotalPriceBeforeDiscount(final int priceBeforeDiscount) {
        outputTitle(TOTAL_PRICE_BEFORE_DISCOUNT_TITLE.getMessage());
        System.out.println(StringUtil.formatByThousandSeparator(priceBeforeDiscount));
        OutputUtil.printEmptyLine();
    }

    private int sumDiscounts(final HashMap<String, Integer> discounts) {
        return discounts.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void outputGifts(final HashMap<String, Integer> gifts) {
        outputTitle(GIFT_MENU_TITLE.getMessage());

        for (Map.Entry<String, Integer> entry : gifts.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "개");
        }
        OutputUtil.printEmptyLine();
    }

    private void outputDiscounts(final HashMap<String, Integer> discounts) {
        outputTitle(BENEFITS_TITLE.getMessage());

        for (Map.Entry<String, Integer> discountEntry : discounts.entrySet()) {
            System.out.println(discountEntry.getKey() + ": -"
                    + StringUtil.formatByThousandSeparator(discountEntry.getValue()) + "원");
        }
        OutputUtil.printEmptyLine();
    }


    private void outputTotalPriceAfterDiscount(final int totalPriceAfterDiscount) {
        outputTitle(TOTAL_PRICE_AFTER_DISCOUNT_TITLE.getMessage());
        System.out.println(StringUtil.formatByThousandSeparator(totalPriceAfterDiscount));
        OutputUtil.printEmptyLine();
    }

    private void outputTitle(String title) {
        System.out.println("<" + title + ">");
    }
}
