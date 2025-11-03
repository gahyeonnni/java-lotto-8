package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.service.InputService;
import lotto.service.LottoService;
import lotto.view.OutputView;
import java.util.List;
import java.util.Map;

public class LottoGame {
    private final InputService inputService;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoGame(InputService inputService, OutputView outputView, LottoService lottoService) {
        this.inputService = inputService;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        int purchaseCount = getValidPurchaseCount();
        List<Lotto> purchased = lottoService.createLottos(purchaseCount);
        WinningLotto winning = getValidWinningLotto();
        Map<LottoRank, Integer> result = lottoService.checkResults(purchased, winning);
        double rate = lottoService.calculateProfitRate(result, purchaseCount * 1000);
        outputView.printWinningStatistics(result, rate);
        Console.close();
    }

    private int getValidPurchaseCount() {
        while (true) {
            try {
                return inputService.getPurchaseCount();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private WinningLotto getValidWinningLotto() {
        List<Integer> winningNumbers = getValidWinningNumbers();
        int bonusNumber = getValidBonusNumber(winningNumbers);
        return inputService.createWinningLotto(winningNumbers, bonusNumber);
    }

    private List<Integer> getValidWinningNumbers() {
        while (true) {
            try {
                return inputService.getWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int getValidBonusNumber(List<Integer> winningNumbers) {
        while (true) {
            try {
                return inputService.getBonusNumber(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
