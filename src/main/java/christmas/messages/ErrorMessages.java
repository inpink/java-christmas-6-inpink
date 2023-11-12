package christmas.messages;

public enum ErrorMessages {

    INVALID_DATE_INPUT_RE_ENTER_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER_INPUT_RE_ENTER_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_NEGATIVE_VALUE_MESSAGE("음수값은 사용할 수 없습니다."),
    INVALID_DIVISION_BY_ZERO_MESSAGE("0으로 나눌 수없습니다.");

    private final String message;
    private static final String prefix = "[ERROR] ";

    ErrorMessages(final String message) {
        this.message = prefix + message;
    }

    public String getMessage() {
        return message;
    }
}
