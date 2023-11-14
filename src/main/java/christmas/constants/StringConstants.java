package christmas.constants;

public enum StringConstants {
    SEPERATE_STANDARD(",");

    private final String value;

    StringConstants(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
