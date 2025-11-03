package lotto.domain;

import lotto.message.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<LottoNumber> numbers;

    public Lotto(List<LottoNumber> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<LottoNumber> numbers) {
        validateSize(numbers);
        validateUnique(numbers);
    }

    private void validateSize(List<LottoNumber> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.SIZE_OF_WINNING_NUMBER.message());
        }
    }

    private void validateUnique(List<LottoNumber> numbers) {
        Set<LottoNumber> unique = new HashSet<>(numbers);
        if (unique.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_NUMBER.message());
        }
    }

    public List<LottoNumber> getNumbers() {
        return List.copyOf(numbers);
    }
}
