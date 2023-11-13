package christmas.domain.menu;

import static christmas.messages.ErrorMessages.NOT_EXIST_MENU;

import christmas.util.ExceptionUtil;
import java.util.Arrays;
import java.util.stream.Stream;

public enum Menu { //이미 정해져있으니까 enum. 만약 매번 새롭게 만들어준다면 abstract class 썼을것
    APPETIZERS("에피타이저", Appetizer.values()),
    MAIN_DISHES("메인", MainDish.values()),
    DESSERTS("디저트", Dessert.values()),
    DRINKS("음료", Drink.values()); //Drink도 상수값으로 필요하기에 valuse를 가져옴. Event에서는 계산하는 용도이므로 람다씀

    private final String description;
    private final Item[] items;

    Menu(final String description, final Item[] items) {
        this.description = description;
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public Item[] getItems() {
        return items;
    }

    public static Menu findMenu(final String itemName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Arrays.stream(menu.items)
                        .anyMatch(item -> item.hasItem(itemName)))
                .findFirst()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException(NOT_EXIST_MENU.getMessage()));
    }

    public static Item findItem(final String itemName) {
        return getAllItemsStream()
                .filter(item -> item.hasItem(itemName))
                .findFirst()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException(NOT_EXIST_MENU.getMessage()));
    }

    public static boolean hasItem(final String itemName) {
        return getAllItemsStream()
                .anyMatch(item -> item.hasItem(itemName));
    }

    public boolean contains(final String itemName) {
        return Arrays.stream(items)
                .anyMatch(item -> item.getName().equalsIgnoreCase(itemName));
    }

    private static Stream<Item> getAllItemsStream() {
        return Arrays.stream(Menu.values())
                .flatMap(menu -> Arrays.stream(menu.items));
    }
}
