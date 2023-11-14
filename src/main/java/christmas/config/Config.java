package christmas.config;

import christmas.controller.OrderController;
import christmas.service.OrderService;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;

public interface Config {
    OrderController orderController();

    OrderService orderService();

    StartView startView();

    InputView inputView();

    OutputView outputView();
}
