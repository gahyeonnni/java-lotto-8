package lotto;

import lotto.domain.LottoRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @DisplayName("일치 개수와 보너스 여부에 따라 올바른 등수가 반환된다.")
    @ParameterizedTest
    @CsvSource({
            "6,false,FIRST",
            "5,true,SECOND",
            "5,false,THIRD",
            "4,false,FOURTH",
            "3,false,FIFTH"
    })
    void 등수_계산_정상(int match, boolean bonus, LottoRank expected) {
        assertThat(LottoRank.checkRank(match, bonus)).isEqualTo(expected);
    }

    @DisplayName("일치 개수가 3 미만이면 NONE을 반환한다.")
    @ParameterizedTest
    @CsvSource({
            "2,false",
            "1,false",
            "0,false"
    })
    void 등수_계산_비정상(int match, boolean bonus) {
        assertThat(LottoRank.checkRank(match, bonus)).isEqualTo(LottoRank.NONE);
    }
}
