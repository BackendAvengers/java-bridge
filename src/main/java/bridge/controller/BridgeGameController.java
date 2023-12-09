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
        return execution(() -> requestBridgeSize());
    }

    private List<String> requestBridgeSize() {
        int size = inputView.readBridgeSize();
        return bridgeGame.makeBridge(size);
    }

    private void startGame(List<String> bridge) {
        String gameState = execution(() -> runGame(bridge));
        if ("R".equals(gameState)) {
            startGame(bridge);
        }
        if ("Q".equals(gameState)) {
            int attemptCount = bridgeGame.getAttemptCount();
            String lastBridge = bridgeGame.getLastBridge();
            outputView.printResult(lastBridge, attemptCount, false);
        }
        if ("E".equals(gameState)) {
            int attemptCount = bridgeGame.getAttemptCount();
            String lastBridge = bridgeGame.getLastBridge();
            outputView.printResult(lastBridge, attemptCount, true);
        }

    }

    private String runGame(List<String> bridge) {

        for (int i = 0; i < bridge.size(); i++) {
            String symbol = inputView.readMoving();
            String move = bridgeGame.move(bridge.get(i), symbol);
            outputView.printMap(move);
            if (bridgeGame.isSuccessGame(bridge.get(i), symbol)) {
                continue;
            }
            String retrySymbol = inputView.readGameCommand();
            if ("R".equals(retrySymbol)) {
                bridgeGame.retry();
                return "R";
            }
            if ("Q".equals(retrySymbol)) {
                return "Q";
            }
        }
        return "E";
    }


}
