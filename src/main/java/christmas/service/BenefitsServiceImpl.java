package christmas.service;

import christmas.domain.entity.Money;
import christmas.domain.entity.event.Benefits;
import christmas.domain.entity.event.Event;
import christmas.domain.entity.menu.Items;
import java.time.LocalDate;

public class BenefitsServiceImpl implements BenefitsService {


    @Override
    public Benefits calculate(final LocalDate dateOfVisit,
                              final Items items) {
        final Money totalPrice = items.calcTotalPrice();
        return Event.findBenefits(dateOfVisit, items, totalPrice);
    }
}
