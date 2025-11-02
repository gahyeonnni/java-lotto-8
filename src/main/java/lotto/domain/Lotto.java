package lotto.lotto;

import lotto.errorMessage.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkUniqueNumber(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void checkUniqueNumber(List <Integer> numbers) {
        Set<Integer> uniq = new HashSet<>(numbers);
        if (uniq.size() != 6) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_NUMBER.message());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
