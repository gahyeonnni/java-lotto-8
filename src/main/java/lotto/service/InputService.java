package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
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

    public WinningLotto getWinningLotto() {
        List<Integer> inputNumbers = validator.parseLottoInput(inputView.getWinningNumber());
        int bonus = validator.parseBonusInput(inputView.getBonusNumber());
        List<LottoNumber> numbers = inputNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());
        return new WinningLotto(new Lotto(numbers), bonus);
    }
}
