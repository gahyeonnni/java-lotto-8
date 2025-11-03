package lotto.domain;

import lotto.message.ErrorMessage;

public class WinningLotto {
    private final Lotto winningLotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(Lotto winningLotto, int bonusNumber) {
        LottoNumber bonus = new LottoNumber(bonusNumber);
        if (winningLotto.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.message());
        }
        this.winningLotto = winningLotto;
        this.bonusNumber = bonus;
    }

    public LottoRank match(Lotto lotto) {
        int matchCount = (int) lotto.getNumbers().stream()
                .filter(winningLotto.getNumbers()::contains)
                .count();
        boolean bonusMatch = lotto.getNumbers().contains(bonusNumber);
        return LottoRank.checkRank(matchCount, bonusMatch);
    }
}
