package christmas.service;

import christmas.domain.entity.event.Benefits;
import christmas.domain.entity.menu.Items;
import java.time.LocalDate;

public interface BenefitsService {
    Benefits calculate(final LocalDate dateOfVisit,
                       final Items items);
}
