package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.message.IOMessage;

public class InputView {
    public String getAmount() {
        System.out.println(IOMessage.ASK_PURCHASE_AMOUNT.message());
        return Console.readLine();
    }

    public String getWinningNumber() {
        System.out.println(IOMessage.ASK_WINNING_NUMBERS.message());
        return Console.readLine();
    }

    public String getBonusNumber() {
        System.out.println(IOMessage.ASK_BONUS_NUMBER.message());
        return Console.readLine();
    }
}
