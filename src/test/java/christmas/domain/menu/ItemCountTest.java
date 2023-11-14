package christmas.domain.menu;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ItemCountTest {

    @ParameterizedTest(name = "값 {0}을 담는 ItemCount 객체가 생성된다.")
    @ValueSource(strings = {"1", "2", "2100000000"})
    public void 정상_아이템_개수_객체_생성(final String count) {
        // When
        final ItemCount result = ItemCount.create(count);

        // Then
        assertThat(result.getCount()).isEqualTo(Integer.parseInt(count));
    }

    @ParameterizedTest(name = "값 {0}은 ItemCount 객체에 담을 수 없다.")
    @ValueSource(strings = {"-1", "0", "2200000000", " "})
    public void 한개_이상의_정수_값만_담을_수_있음(final String count) {
        // When && Then
        assertThatThrownBy(() -> ItemCount.create(count))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
