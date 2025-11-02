package lotto.message;

public enum ErrorMessage {
    ERROR_INPUT("[ERROR] 유효하지 않은 입력입니다."),
    NOT_POSITIVE("[ERROR] 양수가 아닙니다."),
    INVALID_PURCHASE_UNIT("[ERROR] 구입 금액은 1000원 단위여야 합니다."),
    NON_NUMERIC("[ERROR] 숫자가 아닙니다."),
    COUNT_OUT_OF_RANGE("[ERROR] 범위를 초과했습니다."),
    NUMBER_OUT_OF_RANGE("[ERROR] 로또 번호는 1부터 45 사이여야 합니다."),
    DUPLICATE_BONUS_NUMBER("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    DUPLICATE_WINNING_NUMBER("[ERROR] 당첨 번호는 중복될 수 없습니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
