package christmas.config;

import christmas.controller.OrderController;
import christmas.repository.MemoryOrderRepository;
import christmas.repository.Repository;
import christmas.service.BenefitsService;
import christmas.service.BenefitsServiceImpl;
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
    public BenefitsService benefitsService() {
        return LazyHolder.benefitsService;
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

    @Override
    public Repository memoryOrderRepository() {
        return LazyHolder.memoryOrderRepository;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();
        private static final StartView startView = createStartView();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final Repository memoryOrderRepository = createMemoryOrderRepository();
        private static final BenefitsService benefitsService = createBenefitsService();

        private static final OrderService orderService = createOrderService();
        private static final OrderController orderController = createOrderController();

        private static OrderController createOrderController() {
            return new OrderController(
                    startView,
                    inputView,
                    outputView,
                    orderService);
        }

        private static BenefitsService createBenefitsService() {
            return new BenefitsServiceImpl();
        }

        private static OrderService createOrderService() {
            return new OrderServiceImpl(benefitsService, memoryOrderRepository);
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

        private static Repository createMemoryOrderRepository() {
            return new MemoryOrderRepository();
        }
    }
}
