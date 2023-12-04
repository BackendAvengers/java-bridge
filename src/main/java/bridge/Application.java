package bridge;

public class Application {
    public static void main(String[] args) {
        int size = InputView.readBridgeSize();
        BridgeNumberGenerator numberGenerator = new BridgeRandomNumberGenerator();
        BridgeGame game = new BridgeGame(numberGenerator, size);

        boolean isRunning = true;
        while (isRunning) {
            String nextPosition = InputView.readMoving();
            boolean continueGame = game.move(nextPosition);
            if (!continueGame) {
                isRunning = game.retry();
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
