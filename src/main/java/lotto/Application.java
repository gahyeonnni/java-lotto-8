package lotto;

import lotto.controller.Config;
import lotto.controller.LottoGame;

public class Application {
    public static void main(String[] args) {
        Config config = new Config();
        LottoGame lottoGame = config.createGame();
        lottoGame.run();
    }
}
