package christmas.view;

import static christmas.messages.StartMessages.ANNOUNCE_MAX_MENU_COUNT;
import static christmas.messages.StartMessages.ANNOUNCE_MININUM_EVENT_APPLY_PRICE;
import static christmas.messages.StartMessages.ANNOUNCE_ONLY_BEVERAGE_NOT_ORDERABLE;
import static christmas.messages.StartMessages.WELCOME;

import christmas.domain.menu.Item;
import christmas.domain.menu.Menu;
import christmas.util.OutputUtil;

public class ConsoleStartView implements StartView {

    @Override
    public void printWelcomeMessage() {
        System.out.println(WELCOME.getMessage());
        OutputUtil.printEmptyLine();
    }

    @Override
    public void printPrecaution() {
        System.out.println(ANNOUNCE_MININUM_EVENT_APPLY_PRICE.getMessage());
        System.out.println(ANNOUNCE_ONLY_BEVERAGE_NOT_ORDERABLE.getMessage());
        System.out.println(ANNOUNCE_MAX_MENU_COUNT.getMessage());
        OutputUtil.printEmptyLine();
    }

    @Override
    public void printMenu() {
        for (Menu menu : Menu.values()) {
            System.out.println("<" + menu.getDescription() + ">");
            for (Item item : menu.getItems()) {
                System.out.println(item.getName() + "(" + item.getPrice().getAmount() + ")");
            }
            OutputUtil.printEmptyLine();
        }
        OutputUtil.printEmptyLine();
    }
}
