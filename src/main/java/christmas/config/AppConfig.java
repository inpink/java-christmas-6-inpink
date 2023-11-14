package christmas.config;

import christmas.controller.OrderController;
import christmas.service.OrderService;
import christmas.service.OrderServiceImpl;
import christmas.view.ConsoleInputView;
import christmas.view.ConsoleOutputView;
import christmas.view.ConsoleStartView;
import christmas.view.InputView;
import christmas.view.OutputView;
import christmas.view.StartView;

public class AppConfig implements Config {

    public static AppConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public OrderController orderController() {
        return LazyHolder.orderController;
    }

    @Override
    public OrderService orderService() {
        return LazyHolder.orderService;
    }

    @Override
    public StartView startView() {
        return LazyHolder.startView;
    }

    @Override
    public InputView inputView() {
        return LazyHolder.inputView;
    }

    @Override
    public OutputView outputView() {
        return LazyHolder.outputView;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();
        private static final StartView startView = createStartView();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final OrderController orderController = createOrderController();
        private static final OrderService orderService = createOrderService();

        private static OrderController createOrderController() {
            return new OrderController(
                    startView,
                    inputView,
                    outputView,
                    orderService);
        }

        private static OrderService createOrderService() {
            return new OrderServiceImpl();
        }

        private static StartView createStartView() {
            return new ConsoleStartView();
        }

        private static InputView createInputView() {
            return new ConsoleInputView();
        }

        private static OutputView createOutputView() {
            return new ConsoleOutputView();
        }



    }
}
