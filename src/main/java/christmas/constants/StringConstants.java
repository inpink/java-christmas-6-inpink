package christmas.constants;

public enum StringConstants {
    SEPERATE_STANDARD(","),
    KOREAN_WON("원"),
    ITEM_COUNT("개");

    private final String value;

    StringConstants(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
