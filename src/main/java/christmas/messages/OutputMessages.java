package christmas.messages;

import static christmas.constants.IntegerConstants.THIS_MONTH;

public enum OutputMessages {
    NOT_EXIST("없음"),
    KOREAN_WON("원"),
    ITEM_COUNT("개"),
    PREVIEW_TITLE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_TITLE("주문 메뉴"),
    TOTAL_PRICE_BEFORE_DISCOUNT_TITLE("할인 전 총주문 금액"),
    GIFT_MENU_TITLE("증정 메뉴"),
    BENEFITS_TITLE("혜택 내역"),
    TOTAL_BENEFITS_PRICE_TITLE("총혜택 금액"),
    TOTAL_PRICE_AFTER_DISCOUNT_TITLE("할인 후 예상 결제 금액"),
    THIS_MONTH_EVENT_BADGE_TITLE(String.format("%d월 이벤트 배지",THIS_MONTH.getValue()));

    private final String message;

    OutputMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
