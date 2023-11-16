package christmas.service;

import christmas.domain.entity.menu.Items;
import christmas.domain.entity.order.Order;
import java.time.LocalDate;

public interface OrderService {
    Order order(final LocalDate dateOfVisit,
                final Items items);
}
