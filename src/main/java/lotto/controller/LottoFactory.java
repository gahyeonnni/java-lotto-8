package lotto.controller;

import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.generator.LottoNumberGenerator;
import lotto.generator.NumberGenerator;
import lotto.util.InputValidator;

public class LottoFactory {
    public LottoGame createGame() {
        InputValidator validator = new InputValidator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        NumberGenerator generator = new LottoNumberGenerator();
        LottoService lottoService = new LottoService(generator, outputView);
        InputService inputService = new InputService(inputView, validator);

        return new LottoGame(inputService, outputView, lottoService);
    }
}
