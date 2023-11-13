package christmas.domain.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MenuTest {

    @ParameterizedTest(name = "아이템 {0}가 {1} 메뉴에 해당되어야 한다.")
    @CsvSource({
            "양송이수프, APPETIZERS",
            "티본스테이크, MAIN_DISHES",
            "초코케이크, DESSERTS",
            "제로콜라, DRINKS"
    })
    public void 아이템이_어떤메뉴에_해당되는지_찾기(final String itemName, final Menu expectedMenuName) {
        // When
        final Menu result = Menu.findMenu(itemName);

        // Then
        assertThat(result).isEqualTo(expectedMenuName);
    }

    @ParameterizedTest(name = "아이템 {0} 메뉴에 없기에 예외가 발생해야 한다.")
    @CsvSource({
            "없는음식",
            "안팔아요",
            "배고파요",
            "훈이머리",
            "RED_WINE",
            "''"
    })
    public void 메뉴판에_없는아이템을_찾으면_예외_발생(final String itemName) {
        // When && Then
        assertThatThrownBy(() -> Menu.findMenu(itemName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 메뉴입니다.");
    }

    @ParameterizedTest(name = "메뉴 {0}에 아이템 {1}가 있는지 확인하면 {2}다.")
    @CsvSource({
            "DESSERTS, 초코케이크, true",
            "DESSERTS, 도마뱀수프, false",
            "DRINKS, 샴페인, true",
            "DRINKS, 버터맥주, false"
    })
    public void 해당_메뉴에_특정_아이템이름이_들어있는지_상태_확인(final Menu menu,
                                             final String itemName,
                                             final boolean expected) {
        // When
        final boolean result = menu.contains(itemName);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
