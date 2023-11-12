package christmas.domain.menu;

public interface Item {

    String getName();

    int getPrice();

    default boolean hasName(String name) {
        return getName().equalsIgnoreCase(name);
    }
}
