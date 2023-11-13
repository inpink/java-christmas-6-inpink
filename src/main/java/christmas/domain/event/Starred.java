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



    private Set<LocalDate> toLocalDateSet(final Set<Integer> days) {
        return days.stream()
                .map(day -> LocalDate.of(THIS_YEAR.getValue(), THIS_MONTH.getValue(), day))
                .collect(Collectors.toSet());
    }


}
