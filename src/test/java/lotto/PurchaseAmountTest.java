package lotto;

import lotto.message.ErrorMessage;
import lotto.util.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PurchaseAmountTest {
    private final InputValidator validator = new InputValidator();

    @DisplayName("금액이 null 또는 공백이면 예외가 발생한다.")
    @Test
    void 금액_null_or_blank() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());

        assertThatThrownBy(() -> validator.validatePurchaseAmount("   "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.ERROR_INPUT.message());
    }

    @DisplayName("금액이 숫자가 아니면 예외가 발생한다.")
    @Test
    void 금액_숫자아님() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());

        assertThatThrownBy(() -> validator.validatePurchaseAmount("1000원"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());

        assertThatThrownBy(() -> validator.validatePurchaseAmount("1,000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());
    }

    @DisplayName("금액이 음수이거나 0이면 예외가 발생한다.")
    @Test
    void 금액_음수_or_zero() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());

        assertThatThrownBy(() -> validator.validatePurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NOT_POSITIVE.message());
    }

    @DisplayName("금액이 1000원 단위가 아니면 예외가 발생한다.")
    @Test
    void 금액_천원단위아님() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("1509"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PURCHASE_UNIT.message());

        assertThatThrownBy(() -> validator.validatePurchaseAmount("2530"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_PURCHASE_UNIT.message());
    }

    @DisplayName("금액이 숫자이지만 앞뒤 공백이 있는 경우 예외가 발생한다.")
    @Test
    void 금액_공백포함() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount(" 1000 "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.NON_NUMERIC.message());
    }

    @DisplayName("금액이 0으로 시작하는 경우 정상적으로 처리된다.")
    @Test
    void 금액_0으로_시작() {
        assertThatCode(() -> validator.validatePurchaseAmount("01000"))
                .doesNotThrowAnyException();
    }

    @DisplayName("정상적인 금액 입력은 예외가 발생하지 않는다.")
    @Test
    void 금액_정상입력() {
        assertThatCode(() -> validator.validatePurchaseAmount("1000"))
                .doesNotThrowAnyException();

        assertThatCode(() -> validator.validatePurchaseAmount("8000"))
                .doesNotThrowAnyException();

        assertThatCode(() -> validator.validatePurchaseAmount(String.valueOf(1000 * 500)))
                .doesNotThrowAnyException();
    }
}
