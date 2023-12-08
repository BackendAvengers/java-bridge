package bridge.view.input;

import bridge.view.input.constants.InputMessage;
import bridge.view.input.validator.InputValidator;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView extends ConsoleInput{

    private final InputValidator validator;
    private static final String NEW_LINE = System.lineSeparator();

    public InputView(InputValidator validator) {
        this.validator = validator;
    }

    public void printStartMessage() {
        System.out.println(InputMessage.START_MESSAGE.getMessage() + NEW_LINE);
    }

    /**
     * 다리의 길이를 입력받는다.
     */
    public int readBridgeSize() {
        System.out.println(InputMessage.REQUEST_BRIDGE_SIZE.getMessage());
        String input = readLine();
        int size = validator.parseInt(input);
        validator.validateSize(size);
        return size;
    }

    /**
     * 사용자가 이동할 칸을 입력받는다.
     */
    public String readMoving() {
        System.out.println(InputMessage.REQUEST_MOVE_DIRECTION.getMessage());
        String input = readLine();
        validator.validateMoveDirection(input);
        return input;
    }

    /**
     * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
     */
    public String readGameCommand() {
        System.out.println(InputMessage.REQUEST_RETRY_MESSAGE.getMessage());
        String input = readLine();
        validator.validateRetrySymbol(input);
        return input;
    }
}