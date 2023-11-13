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

    public int calcItemCounts() {
        return items.values().stream()
                .mapToInt(ItemCount::getCount)
                .sum();
    }

    private static HashMap<Item, ItemCount> toMap(final String input) {
        final HashMap<Item, ItemCount> items = new HashMap<>();

        for (final String itemNameAndCount : toList(input)) {
            final String[] parts = itemNameAndCount.split("-"); //TODO: 상수화
            IntegerValidator.validateSame(parts.length, 2, errorMessge);

            final Item item = Menu.findItem(parts[0]);
            final ItemCount itemCount = ItemCount.create(parts[1]);

            MapValidator.validateDuplicateKey(items, item, errorMessge);
            items.put(item, itemCount);
        }

        return items;
    }

    private static List<String> toList(final String input) {
        final List<String> itemNamesAndCounts = StringListUtil.seperateBy(input, SEPERATE_STANDARD.getValue())
                .stream()
                .filter(s -> !s.isEmpty())
                .toList();

        return itemNamesAndCounts;
    }


    private void validateSumOfCounts() {
        if (calcItemCounts() > MAX_MENU_ITEM_COUNT.getValue()) {
            ExceptionUtil.throwInvalidValueException(errorMessge);
        }
    }
}
