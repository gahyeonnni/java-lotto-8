package lotto.service;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import lotto.generator.NumberGenerator;
import lotto.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoService {
    private final NumberGenerator generator;
    private final OutputView outputView;

    public LottoService(NumberGenerator generator, OutputView outputView) {
        this.generator = generator;
        this.outputView = outputView;
    }

    public List<Lotto> createLottos(int count) {
        List<Lotto> lottos = IntStream.range(0, count)
                .mapToObj(i -> {
                    List<Integer> numbers = generator.generate(1, 45, 6);
                    return new Lotto(numbers.stream().map(LottoNumber::new).toList());
                }).toList();
        outputView.printPurchasedLotto(lottos);
        return lottos;
    }

    public Map<LottoRank, Integer> checkResults(List<Lotto> purchased, WinningLotto winningLotto) {
        Map<LottoRank, Integer> results = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0);
        }
        for (Lotto lotto : purchased) {
            LottoRank rank = winningLotto.match(lotto);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> results, int purchaseAmount) {
        long totalPrize = 0;
        for (Map.Entry <LottoRank, Integer> entry : results.entrySet()) {
            int count = entry.getValue();
            int prize = entry.getKey().getPrize();
            totalPrize += (long) prize * count;
        }
        double profitRate = ((double) totalPrize / purchaseAmount) * 100;
        return Double.parseDouble(String.format("%.1f", profitRate));
    }

}
