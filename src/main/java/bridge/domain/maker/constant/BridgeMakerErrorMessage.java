package bridge.domain.maker.constant;

public enum BridgeMakerErrorMessage {

    INVALID_BRIDGE_SIZE("다리 길이는 3부터 20 사이의 숫자여야 합니다."),
    INVALID_BRIDGE_VALUE("다리를 만들기 위해 필요한 값이 아닙니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    BridgeMakerErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }
}
