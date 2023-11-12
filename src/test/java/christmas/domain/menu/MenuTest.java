package christmas.domain.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.util.ExceptionUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuTest {

    @ParameterizedTest
    @CsvSource({
            "양송이수프, APPETIZERS",
            "티본스테이크, MAIN_DISHES",
            "초코케이크, DESSERTS",
            "제로콜라, DRINKS"
    })
    public void 아이템이_어떤메뉴에_해당되는지_찾기(String itemName, Menu expectedMenuName) {
        // When
        Menu result = Menu.findMenuType(itemName);

        // Then
        assertThat(result).isEqualTo(expectedMenuName);
    }

    @ParameterizedTest
    @CsvSource({
            "없는음식, APPETIZERS",
            "안팔아요, MAIN_DISHES",
            "배고파요, DESSERTS",
            "RED_WINE, DRINKS"
    })
    public void 메뉴판에_없는아이템을_찾으면_예외_발생(String itemName, Menu expectedMenuName) {
        // When && Then
        assertThatThrownBy(() -> Menu.findMenuType(itemName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 메뉴입니다.");
    }

/*    @ParameterizedTest
    @CsvSource({
            "DESSERTS, 초콜릿 케이크, true",
            "DESSERTS, 마법의 물약, false",
            "DRINKS, 레몬 에이드, true",
            "DRINKS, 마법의 물약, false"
    })
    public void contains_테스트(String menuName, String itemName, boolean expected) {
        Menu menu = Menu.valueOf(menuName);
        boolean result = menu.contains(itemName);

        assertEquals(expected, result);
    }*/
}
