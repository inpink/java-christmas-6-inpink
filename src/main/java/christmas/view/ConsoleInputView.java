package christmas.view;


import static christmas.messages.InputMessages.INPUT_EXPECTED_DATE_OF_VISIT;
import static christmas.messages.InputMessages.ORDER_MENU_ITEM_WITH_COUNT;

import christmas.util.InputUtil;

public class ConsoleInputView implements InputView {
    @Override
    public String inputExpectedDateOfVisit() {
        System.out.println(INPUT_EXPECTED_DATE_OF_VISIT.getMessage());
        return InputUtil.inputString();
    }

    @Override
    public String inputOrderItemsWithCounts() {
        System.out.println(ORDER_MENU_ITEM_WITH_COUNT.getMessage());
        return InputUtil.inputString();
    }
}
