package christmas.service;

import christmas.domain.entity.menu.Items;
import christmas.domain.entity.order.Order;
import java.time.LocalDate;

public interface OrderService {
    Order order(LocalDate dateOfVisit,
                Items items);
}
