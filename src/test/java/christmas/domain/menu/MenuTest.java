package christmas.domain.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
    public void 아이템이_어떤메뉴에_해당되는지_찾기(final String itemName, final Menu expectedMenuName) {
        // When
        final Menu result = Menu.findMenuType(itemName);

        // Then
        assertThat(result).isEqualTo(expectedMenuName);
    }

    @ParameterizedTest
    @CsvSource({
            "없는음식",
            "안팔아요",
            "배고파요",
            "RED_WINE",
            "''"
    })
    public void 메뉴판에_없는아이템을_찾으면_예외_발생(final String itemName) {
        // When && Then
        assertThatThrownBy(() -> Menu.findMenuType(itemName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 메뉴입니다.");
    }

    @ParameterizedTest
    @CsvSource({
            "DESSERTS, 초코케이크, true",
            "DESSERTS, 도마뱀수프, false",
            "DRINKS, 샴페인, true",
            "DRINKS, 버터맥주, false"
    })
    public void contains_테스트(final Menu menu, final String itemName, final boolean expected) {
        // When
        final boolean result = menu.contains(itemName);

        // Then
        assertThat(result).isEqualTo(expected);
    }
}
