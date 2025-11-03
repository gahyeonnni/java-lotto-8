package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.message.ErrorMessage;
import lotto.service.InputService;
import lotto.util.InputValidator;
import lotto.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class BonusInputTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("입력이 null이거나 공백이면 예외가 발생한다.")
    @Test
    void 입력_null_or_blank() {
        assertThatThrownBy(() -> validator.parseBonusInput(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.parseBonusInput("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("숫자가 아닌 입력이면 예외가 발생한다.")
    @Test
    void 숫자아닌입력() {
        assertThatThrownBy(() -> validator.parseBonusInput("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());

        assertThatThrownBy(() -> validator.parseBonusInput("1a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());
    }

    @DisplayName("음수이거나 0이면 예외가 발생한다.")
    @Test
    void 음수_또는_제로() {
        assertThatThrownBy(() -> validator.parseBonusInput("-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());
        assertThatThrownBy(() -> new LottoNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NUMBER_OUT_OF_RANGE.message());
    }

    @DisplayName("정수 범위를 초과하면 예외가 발생한다.")
    @Test
    void 범위_벗어남() {
        assertThatThrownBy(() -> validator.parseBonusInput("10000000000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.COUNT_OUT_OF_RANGE.message());
    }

    @DisplayName("정상 입력은 정수로 반환된다.")
    @Test
    void 정상입력() {
        int result = validator.parseBonusInput("7");
        assertThat(result).isEqualTo(7);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호_중복_예외_발생() {
        List<Integer> winningNumbers = List.of(1, 2, 3, 4, 5, 6);
        InputView stubView = new InputView() {
            @Override public String getBonusNumber() { return "1"; }
            @Override public String getWinningNumber() { return "1,2,3,4,5,6"; }
            @Override public String getAmount() { return "1000"; }
        };

        InputService inputService = new InputService(stubView, new InputValidator());

        assertThatThrownBy(() -> inputService.getBonusNumber(winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATE_BONUS_NUMBER.message());
    }


    @DisplayName("보너스 번호가 당첨 번호와 중복되지 않으면 정상적으로 생성된다.")
    @Test
    void 보너스번호_중복없음() {
        List<LottoNumber> winningNumbers = List.of(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );
        Lotto winningLotto = new Lotto(winningNumbers);
        assertThatCode(() -> new WinningLotto(winningLotto, 7))
                .doesNotThrowAnyException();
    }
}
