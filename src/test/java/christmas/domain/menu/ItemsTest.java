package christmas.domain.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.util.ExceptionUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ItemsTest {

    @ParameterizedTest(name = "값 {1}은 총 {0}개의 아이템을 담는 ItemCount 객체가 생성된다.")
    @CsvSource({
            "'시저샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1', 7",
            "'해산물파스타-2,레드와인-1,초코케이크-1', 4",
            "'타파스-1,제로콜라-1', 2",
            "' 타 파스 - 1,  제로콜라 - 1  ', 2", // 오타 교정: 공백 제거
            "',,,,,타파스-1,,,,,,제로콜라-1,,,,,,,', 2" // 오타 교정 : 아이템 사이의 콤마 제거
    })
    public void 정상_아이템맵_객체_생성(final String input, final int count) {
        // When
        final Items result = Items.create(input);

        // Then
        assertThat(result.calcItemCounts()).isEqualTo(count);
    }

    @ParameterizedTest(name = "{0} 값은 아이템들로 저장할 수 없다.")
    @ValueSource( strings = {"없는샐러드-1, 티본스테이크-1, 크리스마스파스타-1, 제로콜라-3, 아이스크림-1", // 없는 메뉴
            "레드와인-15,레드와인-1,초코케이크-1", // 중복 메뉴
            "해산물파스타-15,레드와인-15,초코케이크-1", // 합 개수가 20개를 초과
            "해산물파스타-21,레드와인-1,초코케이크-1", // 각 개수가 20개를 초과
            "해산물파스타-0,레드와인-1,초코케이크-1", // 개수 지정 안함
            "해산물파스타-k,레드와인-1,초코케이크-1", // 숫자가 아닌 개수
            "해산물,파스타-k,레드와인-1,초코케이크-1", // 아이템 사이가 아닌 곳에 콤마가 있음
            " " // 없는 값
    })
    public void 정상_아이템맵_객체_생성(final String input) {
        // When && Then
        assertThatThrownBy(() -> Items.create(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
}
