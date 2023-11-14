package christmas;

import christmas.config.AppConfig;
import christmas.config.Config;
import christmas.controller.OrderController;

public class Application {
    public static void main(String[] args) {
        Config config = generateConfig();
        OrderController orderController = generateOrderController(config);
        orderController.order();
    }

    private static Config generateConfig() {
        return AppConfig.getInstance();
    }

    private static OrderController generateOrderController(Config config) {
        return config.orderController();
    }
}
