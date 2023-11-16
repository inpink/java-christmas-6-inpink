package christmas.domain.entity.menu;

import static christmas.messages.ErrorMessages.NOT_EXIST_MENU;

import christmas.util.ExceptionUtil;
import java.util.Arrays;
import java.util.stream.Stream;

public enum Menu {
    APPETIZERS("에피타이저", Appetizer.values()),
    MAIN_DISHES("메인", MainDish.values()),
    DESSERTS("디저트", Dessert.values()),
    DRINKS("음료", Drink.values());

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

    public static Menu findMenu(final Item item) {
        return Arrays.stream(Menu.values())
                .filter(menu -> Arrays.stream(menu.items)
                        .anyMatch(menuItem -> menuItem.equals(item)))
                .findFirst()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException(NOT_EXIST_MENU.getMessage()));
    }

    public static Item findItem(final String itemName) {
        return getAllItemsStream()
                .filter(item -> item.hasItem(itemName))
                .findFirst()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException(NOT_EXIST_MENU.getMessage()));
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
