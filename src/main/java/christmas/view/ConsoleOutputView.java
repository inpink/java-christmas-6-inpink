package christmas.view;

import static christmas.messages.OutputMessages.ANNOUNCE_BEVERAGE_ONLY_NOT_ORDERABLE;
import static christmas.messages.OutputMessages.ANNOUNCE_MAX_MENU_COUNT;
import static christmas.messages.OutputMessages.ANNOUNCE_MININUM_EVENT_APPLY_PRICE;
import static christmas.messages.OutputMessages.WELCOME_MESSAGE;

import christmas.domain.menu.Item;
import christmas.domain.menu.Menu;
import christmas.util.OutputUtil;

public class ConsoleOutputView implements OutputView {

    @Override
    public void outputWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE.getMessage());
        OutputUtil.printEmptyLine();
    }

    @Override
    public void outputPrecaution() {
        System.out.println(ANNOUNCE_MININUM_EVENT_APPLY_PRICE.getMessage());
        System.out.println(ANNOUNCE_BEVERAGE_ONLY_NOT_ORDERABLE.getMessage());
        System.out.println(ANNOUNCE_MAX_MENU_COUNT.getMessage());
        OutputUtil.printEmptyLine();
    }

    @Override
    public void outputMenu() {
        for (Menu menu : Menu.values()) {
            System.out.println("<" + menu.getDescription() + ">");
            for (Item item : menu.getItems()) {
                System.out.println(item.getName() + "(" + item.getPrice() + ")");
            }
            OutputUtil.printEmptyLine();
        }
        OutputUtil.printEmptyLine();
    }
}
