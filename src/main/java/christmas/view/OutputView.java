package christmas.view;

import christmas.domain.dto.MemberBadgeDto;
import christmas.domain.dto.OrderBenefitsDto;
import christmas.domain.dto.OrderItemsDto;

public interface OutputView {

    void outputPreviewTitle();

    void outputOrderItems(OrderItemsDto orderItemsDto);

    void outputBenefits(OrderBenefitsDto orderBenefitsDto);

    void outputThisMonthBadge(MemberBadgeDto memberBadgeDto);
}
