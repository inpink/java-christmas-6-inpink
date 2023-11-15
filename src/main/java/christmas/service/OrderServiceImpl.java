package christmas.service;

import christmas.domain.entity.Money;
import christmas.domain.entity.event.Badge;
import christmas.domain.entity.event.Benefits;
import christmas.domain.entity.menu.Items;
import christmas.domain.entity.order.Order;
import christmas.repository.Repository;
import java.time.LocalDate;

public class OrderServiceImpl implements OrderService {

    private final BenefitsService benefitsService;
    private final Repository memoryOrderRepository;

    public OrderServiceImpl(final BenefitsService benefitsService,
                            final Repository memoryOrderRepository) {
        this.benefitsService = benefitsService;
        this.memoryOrderRepository = memoryOrderRepository;
    }

    @Override
    public Order order(final LocalDate dateOfVisit,
                       final Items items){

        final Benefits benefits = benefitsService.calculate(dateOfVisit, items);
        final Money totalDiscount = benefits.calcTotalDiscount();
        final Badge badge = Badge.getBadgeByPrice(totalDiscount);

        final Order order = Order.create(dateOfVisit, items, benefits, badge);

        return (Order) memoryOrderRepository.save(order);
    }
}
