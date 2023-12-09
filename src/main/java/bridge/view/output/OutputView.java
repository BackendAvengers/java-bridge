package bridge.view.output;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(String bridge) {
        System.out.println(bridge);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(String lastBridge, int attemptCount, boolean isSuccess) {
        System.out.println(OutputMessage.RESULT.getMessage());
        System.out.println(lastBridge);
        if (isSuccess) {
            System.out.println(OutputMessage.SUCCESS.getMessage());
        }
        if (!isSuccess) {
            System.out.println(OutputMessage.FAIL.getMessage());
        }
        System.out.println(String.format(OutputMessage.ATTEMPT_COUNT.getMessage(), attemptCount));
    }
}