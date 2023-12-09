package bridge.controller;

import bridge.BridgeGame;
import bridge.BridgeMaker;
import bridge.BridgeRandomNumberGenerator;
import bridge.domain.Bridge;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeGameController {
	private final InputView inputView;
	private final OutputView outputView;
	private BridgeGame bridgeGame;

	public BridgeGameController() {
		this.inputView = new InputView();
		this.outputView = new OutputView();
	}

	public void run() {
		outputView.printStart();
		createBridge();

		do {
			progress();
		} while (bridgeGame.isContinue());

		outputView.printResult(bridgeGame);
	}

	private void createBridge() {
		BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

		Bridge bridge = new Bridge(bridgeMaker.makeBridge(inputView.inputBridgeSize()));
		this.bridgeGame = new BridgeGame(bridge);
	}

	private void progress() {
		bridgeGame.move(inputView.inputMoveBlock());
		outputView.printMap(bridgeGame.getBridgePrintMaker());
		checkRetry();
	}

	private void checkRetry() {
		if (bridgeGame.isFail()) {
			bridgeGame.updateRestart(inputView.inputRetryCommand());
		}
	}
}