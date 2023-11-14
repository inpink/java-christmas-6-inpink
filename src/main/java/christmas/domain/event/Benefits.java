package christmas.domain.event;

import christmas.domain.entity.Money;
import java.util.EnumMap;
import java.util.EnumSet;

public class Benefits {

    private final EnumMap<Event,Benefit> benefits;

    private Benefits(final EnumMap<Event,Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits create(final EnumMap<Event,Benefit> benefits) {
        return new Benefits(benefits);
    }

    public static Benefits createEmpty() {
        return new Benefits(new EnumMap<>(Event.class));
    }

    public boolean hasEvent(final Event event) {
        return benefits.containsKey(event);
    }

    public Money calcTotalDiscount() {
        return benefits.values().stream()
                .map(Benefit::getDiscountPrice)
                .reduce(Money.create(0), Money::add);
    }

    public EnumSet<Event> findAllEvents() {
        if (benefits.isEmpty()) {
            return EnumSet.noneOf(Event.class);
        }
        return EnumSet.copyOf(benefits.keySet());
    }
}
