package bridge.view.input.validator;


public class InputValidator {

    private static final String UP_SYMBOL = "U";
    private static final String DOWN_SYMBOL = "D";
    private static final String RETRY_SYMBOL = "R";
    private static final String EXIT_SYMBOL = "Q";

    public int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
        }
    }

    public void validateSize(int size) {
        if (size < 3 || size > 20) {
            throw new IllegalArgumentException("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }

    public void validateMoveDirection(String input) {
        if (!(UP_SYMBOL.equals(input) || DOWN_SYMBOL.equals(input))) {
            throw new IllegalArgumentException("[ERROR] 위: U, 아래: D를 입력하세요");
        }
    }

    public void validateRetrySymbol(String input) {
        if (!(RETRY_SYMBOL.equals(input) || EXIT_SYMBOL.equals(input))) {
            throw new IllegalArgumentException("[ERROR] 재시도: R, 종료: Q를 입력하세요");
        }
    }
}
