package christmas.util;

public final class ExceptionUtil {

    public static void throwInvalidValueException(final String message) {

        throw new IllegalArgumentException(message);
    }

    public static IllegalArgumentException returnInvalidValueException(final String message) {

       return new IllegalArgumentException(message);
    }

}
