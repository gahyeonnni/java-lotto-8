package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.message.IOMessage;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLotto(List<Lotto> lottos) {
        System.out.printf(IOMessage.PURCHASED_COUNT.message() + "\n", lottos.size());
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public void printWinningStatistics(Map<LottoRank, Integer> results, double profitRate) {
        System.out.println(IOMessage.WINNING_STATISTICS.message());
        System.out.println(IOMessage.DIVIDER.message());

        System.out.printf("3개 일치 (5,000원) - %d개\n", results.get(LottoRank.FIFTH));
        System.out.printf("4개 일치 (50,000원) - %d개\n", results.get(LottoRank.FOURTH));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", results.get(LottoRank.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", results.get(LottoRank.SECOND));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", results.get(LottoRank.FIRST));

        System.out.printf("총 수익률은 %.1f%%입니다.\n", profitRate);
    }
}
