package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.message.ErrorMessage;
import lotto.util.InputValidator;
import lotto.view.InputView;
import java.util.List;
import java.util.stream.Collectors;

public class InputService {
    private final InputView inputView;
    private final InputValidator validator;

    public InputService(InputView inputView, InputValidator validator) {
        this.inputView = inputView;
        this.validator = validator;
    }

    public int getPurchaseCount() {
        String input = inputView.getAmount();
        validator.validatePurchaseAmount(input);
        return Integer.parseInt(input) / 1000;
    }

    public List<Integer> getWinningNumbers() {
        return validator.parseLottoInput(inputView.getWinningNumber());
    }

    public int getBonusNumber(List<Integer> winningNumbers) {
        int bonus = validator.parseBonusInput(inputView.getBonusNumber());
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.message());
        }
        return bonus;
    }

    public WinningLotto createWinningLotto(List<Integer> winningNumbers, int bonus) {
        List<LottoNumber> lottoNumbers = winningNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new WinningLotto(new Lotto(lottoNumbers), bonus);
    }
}
