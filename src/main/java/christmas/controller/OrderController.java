package christmas.controller;

import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;

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

        final String dateOfVisit = inputView.inputExpectedDateOfVisit();
        final String itemsWithCount = inputView.inputOrderItemsWithCounts();

        outputView.outputPreviewTitle();
        outputView.outputOrderMenu();
        outputView.outputTotalPriceBeforeDiscount();
        outputView.outputGifts();
        outputView.outputDiscounts();
        outputView.outputTotalPriceAfterDiscount();
        outputView.outputThisMonthBadge();
    }

    private void printStartMessages() {
        startView.printWelcomeMessage();
        startView.printMenu();
        startView.printPrecaution();
    }

}
