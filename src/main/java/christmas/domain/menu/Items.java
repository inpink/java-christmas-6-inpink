package christmas.domain.menu;

import static christmas.constants.IntegerConstants.MAX_MENU_ITEM_COUNT;
import static christmas.constants.StringConstants.SEPERATE_STANDARD;
import static christmas.messages.ErrorMessages.INVALID_ITEMS_MAP;

import christmas.util.ExceptionUtil;
import christmas.util.StringListUtil;
import christmas.util.StringUtil;
import christmas.validation.IntegerValidator;
import christmas.validation.MapValidator;
import java.util.HashMap;
import java.util.List;

public class Items {

    private static final String errorMessge = INVALID_ITEMS_MAP.getMessage();
    private final HashMap<Item, ItemCount> items;

    private Items(final HashMap<Item, ItemCount> items) {
        MapValidator.validateNotEmpty(items, errorMessge);
        this.items = items;
        validateSumOfCounts();
    }

    public static Items create(final String input) {
        final String deletedSpaces = StringUtil.removeAllSpaces(input);
        final HashMap<Item, ItemCount> items = toMap(deletedSpaces);

        return new Items(items);
    }

    public HashMap<Item, ItemCount> getItems() {
        return new HashMap<>(items);
    }


}
