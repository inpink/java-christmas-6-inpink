package christmas.constants;

public enum PatternConstants {
    THOUSAND_SEPARATOR("###,##0");

    private final String pattern;

    PatternConstants(final String pattern) {
        this.pattern = pattern;
    }

    public String getPattern() {
        return pattern;
    }
}
