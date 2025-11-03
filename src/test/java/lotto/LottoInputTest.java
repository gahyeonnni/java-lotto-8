package lotto;

import lotto.message.ErrorMessage;
import lotto.util.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class LottoInputTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("입력이 null이거나 공백이면 예외가 발생한다.")
    @Test
    void 입력_null_or_blank() {
        assertThatThrownBy(() -> validator.parseLottoInput(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseLottoInput("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("쉼표(,)가 아닌 다른 구분자를 사용하면 예외가 발생한다.")
    @Test
    void 잘못된_구분자_사용() {
        assertThatThrownBy(() -> validator.parseLottoInput("1.2.3.4.5.6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseLottoInput("1/2/3/4/5/6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseLottoInput("1;2;3;4;5;6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("쉼표 개수가 다르거나 숫자 개수가 6개가 아니면 예외가 발생한다.")
    @Test
    void 쉼표_형식_오류() {
        assertThatThrownBy(() -> validator.parseLottoInput("1,2,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseLottoInput("1,2,3,4,5,6,7"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("숫자가 아닌 값이 포함되어 있으면 예외가 발생한다.")
    @Test
    void 숫자아닌값_포함() {
        assertThatThrownBy(() -> validator.parseLottoInput("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseLottoInput("1,2, ,4,5,6"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("정상적인 6개 입력이면 정수 리스트를 반환한다.")
    @Test
    void 정상입력() {
        var result = validator.parseLottoInput("1,2,3,4,5,6");
        assertThat(result).containsExactly(1, 2, 3, 4, 5, 6);
    }

    @DisplayName("로또 번호에범위를 벗어난 값이 포함되면 예외가 발생한다.")
    @Test
    void 범위_벗어난_숫자() {
        var numbers = validator.parseLottoInput("0,2,3,4,5,6");
        assertThatThrownBy(() -> numbers.forEach(n -> new lotto.domain.LottoNumber(n)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_OUT_OF_RANGE.message());

        var numbers2 = validator.parseLottoInput("1,2,3,4,5,46");
        assertThatThrownBy(() -> numbers2.forEach(n -> new lotto.domain.LottoNumber(n)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_OUT_OF_RANGE.message());
    }

    @DisplayName("로또 번호가 중복된 경우 예외가 발생한다.")
    @Test
    void 중복된_숫자() {
        var numbers = validator.parseLottoInput("1,2,3,4,5,5");
        assertThatThrownBy(() -> new lotto.domain.Lotto(
                numbers.stream().map(lotto.domain.LottoNumber::new).toList()
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_WINNING_NUMBER.message());
    }
}
