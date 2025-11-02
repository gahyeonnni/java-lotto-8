package lotto.util;

import lotto.message.ErrorMessage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final int MIN = 1;
    private static final int MAX = 45;
    private static final String MAX_INT_STRING = String.valueOf(Integer.MAX_VALUE);

    public void validatePurchaseAmount(String input) {
        checkNonEmpty(input);
        checkNumeric(input);
        checkIntOverflow(input);
        int value = Integer.parseInt(input);
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NOT_POSITIVE.message());
        }
        if (value % 1000 != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_UNIT.message());
        }
    }

    public List<Integer> parseLottoInput(String input) {
        checkNonEmpty(input);
        if (!input.matches("^(\\d{1,2})(,\\d{1,2}){5}$")) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_INPUT.message());
        }
        List<Integer> numbers = List.of(input.split(","))
                .stream()
                .map(this::parseIntWithOverflowCheck)
                .toList();
        validateLottoNumbers(numbers);
        return numbers;
    }

    public int parseBonusInput(String input) {
        checkNonEmpty(input);
        checkNumeric(input);
        checkIntOverflow(input);
        int value = Integer.parseInt(input);
        checkRange(value, MIN, MAX);
        return value;
    }

    public void validateBonusNumber(int bonus, List<Integer> winningNumbers) {
        checkRange(bonus, MIN, MAX);
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_BONUS_NUMBER.message());
        }
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        Set<Integer> uniq = new HashSet<>(numbers);
        if (uniq.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATE_WINNING_NUMBER.message());
        }
        for (int n : numbers) {
            checkRange(n, MIN, MAX);
        }
    }

    private void checkNonEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_INPUT.message());
        }
    }

    private void checkNumeric(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.NON_NUMERIC.message());
        }
    }

    private void checkRange(int value, int min, int max) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.message());
        }
    }

    private void checkIntOverflow(String input) {
        if (input.length() > MAX_INT_STRING.length()) {
            throw new IllegalArgumentException(ErrorMessage.COUNT_OUT_OF_RANGE.message());
        }
        if (input.length() == MAX_INT_STRING.length() && input.compareTo(MAX_INT_STRING) > 0) {
            throw new IllegalArgumentException(ErrorMessage.COUNT_OUT_OF_RANGE.message());
        }
    }

    private int parseIntWithOverflowCheck(String input) {
        checkNumeric(input);
        checkIntOverflow(input);
        return Integer.parseInt(input);
    }
}
