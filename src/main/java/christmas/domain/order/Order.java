package christmas.domain.order;

import christmas.domain.event.Benefits;
import christmas.domain.menu.Items;
import java.time.LocalDate;

public class Order {

    private final LocalDate dateOfVisit;
    private final Items items;
    private final Benefits benefits;

    private Order(final LocalDate dateOfVisit,
                  final Items items,
                  final Benefits benefits) {
        this.dateOfVisit = dateOfVisit;
        this.items = items;
        this.benefits = benefits;
    }

    public static Order create(final LocalDate dateOfVisit,
                               final Items items,
                               final Benefits benefits) {
        return new Order(dateOfVisit, items, benefits);
    }

}
