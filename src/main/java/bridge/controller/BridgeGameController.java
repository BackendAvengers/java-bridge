package bridge.controller;

import bridge.domain.BridgeGame;
import bridge.view.input.InputView;
import bridge.view.output.OutputView;

import java.util.List;

public class BridgeGameController extends RetryController {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGame bridgeGame;

    public BridgeGameController(InputView inputView, OutputView outputView, BridgeGame bridgeGame) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGame = bridgeGame;
    }

    public void run() {
        List<String> bridge = createGame();
        startGame(bridge);
    }

    private List<String> createGame() {
        inputView.printStartMessage();
        int size = inputView.readBridgeSize();
        return bridgeGame.makeBridge(size);
    }

    private void startGame(List<String> bridge) {
        System.out.println(bridge);
        for (int i = 0; i < bridge.size(); i++) {
            String symbol = inputView.readMoving();
            String move = bridgeGame.move(bridge.get(i), symbol);
            System.out.println(move);
        }
        inputView.readMoving();
    }


}
