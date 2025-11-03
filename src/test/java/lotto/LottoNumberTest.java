package lotto;

import lotto.domain.LottoNumber;
import lotto.message.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoNumberTest {

    @DisplayName("로또 번호가 1~45 사이면 정상적으로 생성된다.")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 8, 16, 29, 45})
    void 로또번호_정상범위_생성(int input) {
        assertThatCode(() -> new LottoNumber(input))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 번호가 1 미만 또는 45 초과이면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100, 999, 57})
    void 로또번호_범위_벗어나면_예외발생(int input) {
        assertThatThrownBy(() -> new LottoNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_OUT_OF_RANGE.message());
    }

    @DisplayName("같은 값을 가진 LottoNumber는 동등하다.")
    @ParameterizedTest
    @ValueSource(ints = {5, 10, 23})
    void 동일값_로또번호는_같다고_판단(int input) {
        LottoNumber num1 = new LottoNumber(input);
        LottoNumber num2 = new LottoNumber(input);

        assertThat(num1).isEqualTo(num2);
        assertThat(num1.hashCode()).isEqualTo(num2.hashCode());
    }
}
