package lotto.message;

public enum IOMessage {
    ASK_PURCHASE_AMOUNT("구입금액을 입력해 주세요."),
    ASK_WINNING_NUMBERS("당첨 번호를 입력해 주세요."),
    ASK_BONUS_NUMBER("보너스 번호를 입력해 주세요."),
    PURCHASED_COUNT("%d개를 구매했습니다."),
    WINNING_STATISTICS("당첨 통계"),
    DIVIDER("---");

    private final String message;

    IOMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
