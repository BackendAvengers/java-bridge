package bridge.view.output;

public enum OutputMessage {
    RESULT("최종 게임 결과"),
    SUCCESS("게임 성공 여부: 성공"),
    FAIL("게임 성공 여부: 실패"),
    ATTEMPT_COUNT("총 시도한 횟수: %s");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
