package lotto.domain;

import lotto.message.ErrorMessage;

public class LottoNumber {
    private final int value;
    private static final int MIN = 1;
    private static final int MAX = 45;

    public LottoNumber(int value) {
        if (value < MIN || value > MAX) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_OUT_OF_RANGE.message());
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof LottoNumber))
            return false;
        return value == ((LottoNumber) o).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
