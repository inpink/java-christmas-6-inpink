package christmas.messages;

import static christmas.constants.IntegerConstants.MAX_MENU_COUNT;
import static christmas.constants.IntegerConstants.MINIMUM_EVENT_APPLY_PRICE;

public enum OutputMessages {
    WELCOME_MESSAGE("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    ANNOUNCE_MININUM_EVENT_APPLY_PRICE(
            String.format("총주문 금액 %,d원 이상부터 이벤트가 적용됩니다.", MINIMUM_EVENT_APPLY_PRICE.getValue())),

    ANNOUNCE_BEVERAGE_ONLY_NOT_ORDERABLE("음료만 주문 시 주문할 수 없습니다."),
    ANNOUNCE_MAX_MENU_COUNT(
            String.format("메뉴는 한 번에 최대 %d개 까지만 주문할 수 있습니다.", MAX_MENU_COUNT.getValue()));

    private final String message;

    OutputMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
