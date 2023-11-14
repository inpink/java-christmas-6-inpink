package christmas.controller;

import static christmas.constants.IntegerConstants.THIS_MONTH;
import static christmas.constants.IntegerConstants.THIS_YEAR;

import christmas.domain.dto.DtoMapper;
import christmas.domain.dto.MemberBadgeDto;
import christmas.domain.dto.OrderBenefitsDto;
import christmas.domain.dto.OrderItemsDto;
import christmas.domain.entity.Money;
import christmas.domain.event.Badge;
import christmas.domain.event.Benefits;
import christmas.domain.event.Event;
import christmas.domain.menu.Items;
import christmas.domain.order.Order;
import christmas.service.OrderService;
import christmas.validation.DateValidator;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;
import java.time.LocalDate;

public class OrderController {

    private final StartView startView;
    private final InputView inputView;
    private final OutputView outputView;
    private final OrderService orderService;


    public OrderController(final StartView startView,
                           final InputView inputView,
                           final OutputView outputView,
                           final OrderService orderService) {
        this.startView = startView;
        this.inputView = inputView;
        this.outputView = outputView;
        this.orderService = orderService;
    }

    public void order() {
        printStartMessages();

        final String inputDateOfVisit = inputView.inputExpectedDateOfVisit();
        DateValidator.validateExistInCalendar(
                THIS_YEAR.toString(),
                THIS_MONTH.toString(),
                inputDateOfVisit);

        final LocalDate dateOfVisit = LocalDate.of(
                THIS_YEAR.getValue(),
                THIS_MONTH.getValue(),
                Integer.parseInt(inputDateOfVisit));

        final String inputItemsWithCounts = inputView.inputOrderItemsWithCounts();
        final Items items = Items.create(inputItemsWithCounts);
        final Money totalPrice = items.calcTotalPrice();

        final Benefits benefits = Event.findBenefits(dateOfVisit, items, totalPrice);
        final Money trickeryDiscount = benefits.calcTrickeryDiscount();
        final Order order = Order.create(dateOfVisit, items, benefits);
        final Badge badge = Badge.getBadgeByPrice(benefits.calcTotalDiscount());

        outputView.outputPreviewTitle();

        final OrderItemsDto orderItemsDto = DtoMapper.toOrderItemsDto(
                items.toMapWithNameKey(),
                totalPrice.getAmount());
        outputView.outputOrderItems(orderItemsDto);

        final OrderBenefitsDto orderBenefitsDto = DtoMapper.toOrderBenefitsDto(
                benefits.toGiftsMapWithNameKey(),
                benefits.toDiscountsMapWithNameKey(),
                totalPrice.getAmount(),
                trickeryDiscount.getAmount());

        outputView.outputBenefits(orderBenefitsDto);

        final MemberBadgeDto memberBadgeDto = DtoMapper.toMemberBadgeDto(badge.name());
        outputView.outputThisMonthBadge(memberBadgeDto);
    }

    private void printStartMessages() {
        startView.printWelcomeMessage();
        startView.printMenu();
        startView.printPrecaution();
    }

}
