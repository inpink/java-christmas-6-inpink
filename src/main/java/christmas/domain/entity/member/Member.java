package christmas.domain.entity.member;

import christmas.domain.entity.event.Badge;
import christmas.domain.entity.order.Orders;

public class Member extends IndexModel {

    private final Orders orders;
    private final Badge badge;

    private Member(Orders orders, Badge badge) {
        this.orders = orders;
        this.badge = badge;
    }
}
