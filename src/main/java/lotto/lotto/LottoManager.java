package lotto.lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoManager {
    private final NumberGenerator numberGenerator;

    public LottoManager(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
    public List<Lotto> createLottos(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new Lotto(numberGenerator.generate(1, 45, 6)))
                .toList();
    }

    public Map<LottoRank, Integer> checkResults(List<Lotto> purchasedLottos, WinningLotto winningLotto) {
        Map<LottoRank, Integer> results = new HashMap<>();
        for (LottoRank rank : LottoRank.values()) {
            results.put(rank, 0);
        }
        for (Lotto lotto : purchasedLottos) {
            int matchCount = (int) lotto.getNumbers().stream()
                    .filter(winningLotto.getWinningLotto().getNumbers()::contains)
                    .count();
            boolean bonusMatch = lotto.getNumbers().contains(winningLotto.getBonusNumber());
            LottoRank rank = LottoRank.checkRank(matchCount, bonusMatch);
            results.put(rank, results.get(rank) + 1);
        }
        return results;
    }

    public double calculateProfitRate(Map<LottoRank, Integer> results, int purchaseAmount) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getPrize() * entry.getValue())
                .sum();

        double rate = (double) totalPrize / purchaseAmount * 100;
        return Math.round(rate * 10) / 10.0;
    }
}
