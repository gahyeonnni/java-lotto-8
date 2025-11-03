package lotto.generator;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoNumberGenerator implements NumberGenerator {
    @Override
    public List <Integer> generate(int min, int max, int count) {
        List<Integer> lotto = new ArrayList<>(Randoms.pickUniqueNumbersInRange(min, max, count));
        Collections.sort(lotto);
        return lotto;
    }
}