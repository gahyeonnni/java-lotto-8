package lotto.controller;

import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.lotto.LottoManager;
import lotto.lotto.LottoNumberGenerator;
import lotto.lotto.NumberGenerator;
import lotto.util.InputValidator;

//Factory 로 이름을 바꿔라
public class Config {
    public LottoGame createGame() {
        InputValidator validator = new InputValidator();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        NumberGenerator numberGenerator = new LottoNumberGenerator();
        LottoManager lottoManager = new LottoManager(numberGenerator);

        return new LottoGame(inputView, outputView, validator, lottoManager);
    }
}