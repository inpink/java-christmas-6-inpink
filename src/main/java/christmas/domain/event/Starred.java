package christmas.domain.event;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public enum Starred {
    YES(Set.of(3, 10, 17, 24, 31, 25)),
    NO(Set.of());

    private final Set<LocalDate> dates;

    Starred(final Set<Integer> days) {
        this.dates = toLocalDateSet(days);
    }

    public static boolean isStarred(final LocalDate date) {
        return YES.isContaining(date);
    }

    public static Starred findStarred(final LocalDate date) {
        return Arrays.stream(Starred.values())
                .filter(starred -> starred.isContaining(date))
                .findFirst()
                .orElse(NO);
    }

    private Set<LocalDate> toLocalDateSet(final Set<Integer> days) {
        return days.stream()
                .map(day -> LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), day))
                .collect(Collectors.toSet());
    }

    private boolean isContaining(final LocalDate date) {
        return dates.contains(date);
    }
}
