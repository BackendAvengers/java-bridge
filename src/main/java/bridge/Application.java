package bridge;

import bridge.controller.BridgeGameController;
import bridge.domain.BridgeGame;
import bridge.domain.maker.BridgeMaker;
import bridge.domain.maker.BridgeRandomNumberGenerator;
import bridge.view.input.InputView;
import bridge.view.input.validator.InputValidator;
import bridge.view.output.OutputView;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        BridgeGameController bridgeGameController =
                new BridgeGameController(new InputView(new InputValidator()), new OutputView(), new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator())));

        bridgeGameController.run();
    }
}