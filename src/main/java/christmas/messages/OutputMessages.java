package christmas.messages;

public enum OutputMessages {
    PREVIEW_TITLE_MESSAGE("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_TITLE_MESSAGE("주문 메뉴"),
    TOTAL_PRICE_BEFORE_DISCOUNT_TITLE_MESSAGE("할인 전 총주문 금액"),
    GIFT_MENU_TITLE_MESSAGE("증정 메뉴"),
    BENEFITS_TITLE_MESSAGE("혜택 내역"),
    TOTAL_BENEFITS_PRICE_TITLE_MESSAGE("총혜택 금액"),
    TOTAL_PRICE_AFTER_DISCOUNT_TITLE_MESSAGE("할인 후 예상 결제 금액"),
    DECEMBER_EVENT_BADGE_TITLE_MESSAGE("12월 이벤트 배지");

    private final String message;

    OutputMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
