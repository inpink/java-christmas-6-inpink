package christmas.domain.entity.order;

import christmas.domain.entity.event.Badge;
import christmas.domain.entity.event.Benefits;
import christmas.domain.entity.menu.Items;
import java.time.LocalDate;

public class Order extends IndexModel {

    private final LocalDate dateOfVisit;
    private final Items items;
    private final Benefits benefits;
    private final Badge badge;

    private Order(final LocalDate dateOfVisit,
                  final Items items,
                  final Benefits benefits,
                  final Badge badge) {
        this.dateOfVisit = dateOfVisit;
        this.items = items;
        this.benefits = benefits;
        this.badge = badge;
    }

    public static Order create(final LocalDate dateOfVisit,
                               final Items items,
                               final Benefits benefits,
                               final Badge badge) {
        return new Order(dateOfVisit, items, benefits, badge);
    }

    public Items getItems() {
        return items;
    }

    public Benefits getBenefits() {
        return benefits;
    }

    public Badge getBadge() {
        return badge;
    }
}
