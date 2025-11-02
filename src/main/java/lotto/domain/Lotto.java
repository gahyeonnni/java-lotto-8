package lotto.domain;

import lotto.message.ErrorMessage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = List.copyOf(numbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Set<Integer> unique = new HashSet<>(numbers);
        if (unique.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_NUMBER.message());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}