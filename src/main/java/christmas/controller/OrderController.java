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

        final LocalDate dateOfVisit = inputAndValidateDateOfVisit();
        final Items items = inputAndCreateOrderItems();
        final Benefits benefits = calculateBenefits(dateOfVisit, items);
        final Money totalDiscount = benefits.calcTotalDiscount();
        final Badge badge = Badge.getBadgeByPrice(totalDiscount);
        final Order order = createOrder(dateOfVisit, items, benefits);

        outputOrder(order, items, benefits, badge);
    }

    private void printStartMessages() {
        startView.printWelcomeMessage();
        startView.printMenu();
        startView.printPrecaution();
    }

    private LocalDate inputAndValidateDateOfVisit() {
        final String inputDateOfVisit = inputView.inputExpectedDateOfVisit();
        DateValidator.validateExistInCalendar(
                THIS_YEAR.toString(),
                THIS_MONTH.toString(),
                inputDateOfVisit
        );
        return LocalDate.of(
                THIS_YEAR.getValue(),
                THIS_MONTH.getValue(),
                Integer.parseInt(inputDateOfVisit)
        );
    }

    private Items inputAndCreateOrderItems() {
        final String inputItemsWithCounts = inputView.inputOrderItemsWithCounts();
        return Items.create(inputItemsWithCounts);
    }

    private Benefits calculateBenefits(final LocalDate dateOfVisit, final Items items) {
        final Money totalPrice = items.calcTotalPrice();
        return Event.findBenefits(dateOfVisit, items, totalPrice);
    }

    private Order createOrder(final LocalDate dateOfVisit, final Items items, final Benefits benefits) {
        return Order.create(dateOfVisit, items, benefits);
    }

    private void outputOrder(final Order order, final Items items, final Benefits benefits, final Badge badge) {
        outputView.outputPreviewTitle();
        outputOrderItems(items);
        outputOrderBenefits(items, benefits);
        outputMemberBadge(badge);
    }

    private void outputOrderItems(final Items items) {
        final Money totalPrice = items.calcTotalPrice();
        final OrderItemsDto orderItemsDto = DtoMapper.toOrderItemsDto(
                items.toMapWithNameKey(),
                totalPrice.getAmount()
        );
        outputView.outputOrderItems(orderItemsDto);
    }

    private void outputOrderBenefits(final Items items, final Benefits benefits) {
        final Money trickeryDiscount = benefits.calcTrickeryDiscount();
        final OrderBenefitsDto orderBenefitsDto = DtoMapper.toOrderBenefitsDto(
                benefits.toGiftsMapWithNameKey(),
                benefits.toDiscountsMapWithNameKey(),
                items.calcTotalPrice().getAmount(),
                trickeryDiscount.getAmount()
        );
        outputView.outputBenefits(orderBenefitsDto);
    }

    private void outputMemberBadge(final Badge badge) {
        final MemberBadgeDto memberBadgeDto = DtoMapper.toMemberBadgeDto(badge.getName());
        outputView.outputThisMonthBadge(memberBadgeDto);
    }
}

