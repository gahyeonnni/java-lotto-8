package lotto.util;

import lotto.message.ErrorMessage;
import java.util.List;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("\\d+");
    private static final String MAX_INT_STRING = String.valueOf(Integer.MAX_VALUE);
    private static final String DELIMITER = ",";

    public void validatePurchaseAmount(String input) {
        int value = parseToInt(input);
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
        List<Integer> numbers = List.of(input.split(DELIMITER))
                .stream().map(this::parseToInt).toList();
        return numbers;
    }

    public int parseBonusInput(String input) {
        return parseToInt(input);
    }

    private int parseToInt(String input) {
        checkNonEmpty(input);
        checkNumeric(input);
        checkIntOverflow(input);
        return Integer.parseInt(input);
    }

    private void checkNonEmpty(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.ERROR_INPUT.message());
        }
    }

    private void checkNumeric(String input) {
        if (!NUMERIC_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(ErrorMessage.NON_NUMERIC.message());
        }
    }

    private void checkIntOverflow(String input) {
        checkLengthOverflow(input);
        checkValueOverflow(input);
    }

    private void checkLengthOverflow(String input) {
        if (input.length() > MAX_INT_STRING.length()) {
            throw new IllegalArgumentException(ErrorMessage.COUNT_OUT_OF_RANGE.message());
        }
    }

    private void checkValueOverflow(String input) {
        if (input.length() == MAX_INT_STRING.length() && input.compareTo(MAX_INT_STRING) > 0) {
            throw new IllegalArgumentException(ErrorMessage.COUNT_OUT_OF_RANGE.message());
        }
    }
}
