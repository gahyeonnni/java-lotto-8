package lotto;

import lotto.controller.LottoFactory;
import lotto.controller.LottoGame;

public class Application {
    public static void main(String[] args) {
        LottoFactory factory = new LottoFactory();
        LottoGame game = factory.createGame();
        game.run();
    }
}