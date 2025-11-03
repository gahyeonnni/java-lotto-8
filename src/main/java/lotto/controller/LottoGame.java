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
        try {
            int purchaseCount = inputService.getPurchaseCount();
            List<Lotto> purchased = lottoService.createLottos(purchaseCount);
            WinningLotto winning = inputService.getWinningLotto();
            Map<LottoRank, Integer> result = lottoService.checkResults(purchased, winning);
            double rate = lottoService.calculateProfitRate(result, purchaseCount * 1000);
            outputView.printWinningStatistics(result, rate);
            Console.close();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
}
