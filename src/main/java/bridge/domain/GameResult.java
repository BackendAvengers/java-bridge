package bridge.domain;

public enum GameResult {
    SUCCESS("성공"),
    CONTINUE("계속"),
    FAIL("실패");

    private final String statusMessage;

    GameResult(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}