package christmas.domain.event;

import christmas.domain.entity.Money;
import christmas.domain.menu.Item;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Benefits {

    private final EnumMap<Event, Benefit> benefits;

    private Benefits(final EnumMap<Event, Benefit> benefits) {
        this.benefits = benefits;
    }

    public static Benefits create(final EnumMap<Event, Benefit> benefits) {
        return new Benefits(benefits);
    }

    public static Benefits createEmpty() {
        return new Benefits(new EnumMap<>(Event.class));
    }

    public boolean hasEvent(final Event event) {
        return benefits.containsKey(event);
    }

    public Money calcTotalDiscount() {
        return benefits.values()
                .stream()
                .map(Benefit::getDiscountPrice)
                .reduce(Money.create(0), Money::add);
    }

    public Money calcTrickeryDiscount() {
        return benefits.values()
                .stream()
                .map(Benefit::getGiftsPrice)
                .reduce(Money.create(0), Money::add);
    }

    public EnumSet<Event> findAllEvents() {
        if (benefits.isEmpty()) {
            return EnumSet.noneOf(Event.class);
        }
        return EnumSet.copyOf(benefits.keySet());
    }

    public Set<Entry<Event, Benefit>> toEntrySet() {
        return benefits.entrySet();
    }

    public Map<String, Integer> toGiftsMapWithNameKey() {
        return benefits.values()
                .stream()
                .flatMap(benefit -> benefit.getGiftsByList().stream())
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(item -> 1)));
    }

    public Map<String, Integer> toDiscountsMapWithNameKey() {
        return toEntrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> {
                            Event event = entry.getKey();
                            return event.getDescription();
                        },
                        entry -> {
                            Benefit benefit = entry.getValue();
                            Money discountPrice = benefit.getDiscountPrice();
                            return discountPrice.getAmount();
                        }
                ));
    }
}