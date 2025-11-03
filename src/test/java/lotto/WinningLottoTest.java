package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WinningLottoTest {

    @DisplayName("6개 일치하면 1등이다.")
    @Test
    void match_first_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(6)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.FIRST);
    }

    @DisplayName("5개 일치 + 보너스 번호가 일치하면 2등이다.")
    @Test
    void match_second_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(7)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.SECOND);
    }

    @DisplayName("5개 번호만 일치하면 3등이다.")
    @Test
    void match_third_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(5), new LottoNumber(11)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.THIRD);
    }

    @DisplayName("4개 번호만 일치하면 4등이다.")
    @Test
    void match_fourth_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(4),
                new LottoNumber(10), new LottoNumber(11)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.FOURTH);
    }

    @DisplayName("3개 번호만 일치하면 5등이다.")
    @Test
    void match_fifth_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(3), new LottoNumber(8),
                new LottoNumber(9), new LottoNumber(10)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.FIFTH);
    }

    @DisplayName("3개 미만으로 일치하면 등수가 없다.")
    @Test
    void match_none_rank() {
        WinningLotto winning = new WinningLotto(
                new Lotto(List.of(
                        new LottoNumber(1), new LottoNumber(2),
                        new LottoNumber(3), new LottoNumber(4),
                        new LottoNumber(5), new LottoNumber(6)
                )),
                7
        );

        Lotto lotto = new Lotto(List.of(
                new LottoNumber(1), new LottoNumber(2),
                new LottoNumber(8), new LottoNumber(9),
                new LottoNumber(10), new LottoNumber(11)
        ));

        assertThat(winning.match(lotto)).isEqualTo(LottoRank.NONE);
    }
}
