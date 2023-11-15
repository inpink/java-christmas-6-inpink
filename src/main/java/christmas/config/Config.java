package christmas.config;

import christmas.controller.OrderController;
import christmas.repository.Repository;
import christmas.service.BenefitsService;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;

public interface Config {
    OrderController orderController();

    OrderService orderService();

    BenefitsService benefitsService();

    StartView startView();

    InputView inputView();

    OutputView outputView();

    Repository memoryOrderRepository();
}
