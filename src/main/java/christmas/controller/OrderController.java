package christmas.controller;

import static christmas.messages.ErrorMessages.INVALID_DATE_RE_ENTER;
import static christmas.messages.ErrorMessages.INVALID_ORDER_RE_ENTER;

import christmas.domain.dto.DtoMapper;
import christmas.domain.dto.MemberBadgeDto;
import christmas.domain.dto.OrderBenefitsDto;
import christmas.domain.dto.OrderItemsDto;
import christmas.domain.entity.DateOfVisit;
import christmas.domain.entity.Money;
import christmas.domain.event.Badge;
import christmas.domain.event.Benefits;
import christmas.domain.event.Event;
import christmas.domain.menu.Items;
import christmas.domain.order.Order;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;
import java.time.LocalDate;
import java.util.function.Supplier;

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

        final LocalDate dateOfVisit = safelyGetInput(
                this::inputAndValidateDateOfVisit,
                INVALID_DATE_RE_ENTER.getMessage()
        );

        final Items items = safelyGetInput(
                this::inputAndCreateOrderItems,
                INVALID_ORDER_RE_ENTER.getMessage()
        );

        final Benefits benefits = calculateBenefits(dateOfVisit, items);
        final Money totalDiscount = benefits.calcTotalDiscount();
        final Badge badge = Badge.getBadgeByPrice(totalDiscount);
        final Order order = createOrder(dateOfVisit, items, benefits);
        /*final Member member = MemberService.findById(id);
        member.addOrder(order);
        memeber.updateBadge(badge);*/

        outputOrder(items, benefits, badge);
    }

    private void printStartMessages() {
        startView.printWelcomeMessage();
        startView.printMenu();
        startView.printPrecaution();
    }

    private LocalDate inputAndValidateDateOfVisit() {
        final String inputDateOfVisit = inputView.inputExpectedDateOfVisit();
        return DateOfVisit.toLocalDate(inputDateOfVisit);
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

    private void outputOrder(final Items items, final Benefits benefits, final Badge badge) {
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

    private <T> T safelyGetInput(Supplier<T> inputSupplier, String errorMessage) {
        while (true) {
            try {
                return inputSupplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(errorMessage);
            }
        }
    }
}

