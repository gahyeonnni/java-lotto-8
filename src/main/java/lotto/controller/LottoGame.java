package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;
import lotto.lotto.LottoManager;
import lotto.util.InputValidator;

import java.util.List;
import java.util.Map;

public class LottoGame {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator validator;
    private final LottoManager lottoManager;

    public LottoGame(
            InputView inputView,
            OutputView outputView,
            InputValidator validator,
            LottoManager lottoManager
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.validator = validator;
        this.lottoManager = lottoManager;
    }

    public void run() {
        try {
            int purchaseCount = getPurchaseCount();
            List<Lotto> purchasedLottos = lottoManager.createLottos(purchaseCount);
            outputView.printPurchasedLotto(purchasedLottos);
            WinningLotto winningLotto = getWinningLotto();
            Map<LottoRank, Integer> results = lottoManager.checkResults(purchasedLottos, winningLotto);
            double rate = lottoManager.calculateProfitRate(results, purchaseCount * 1000);
            outputView.printWinningStatistics(results, rate);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }

    private int getPurchaseCount() {
        String amountInput = inputView.getAmount();
        validator.validatePurchaseAmount(amountInput);
        return Integer.parseInt(amountInput) / 1000;
    }

    private WinningLotto getWinningLotto() {
        List<Integer> winningNumbers = validator.parseLottoInput(inputView.getWinningNumber());
        int bonus = validator.parseBonusInput(inputView.getBonusNumber());
        validator.validateBonusNumber(bonus, winningNumbers);
        return new WinningLotto(new Lotto(winningNumbers), bonus);
    }
}
