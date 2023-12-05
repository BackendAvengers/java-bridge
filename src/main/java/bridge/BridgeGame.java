package bridge;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private BridgeMaker bridgeMaker;
    private List<Integer> bridgeStructure;
    private int index;
    private int size;
    private int trialCount;

    public BridgeGame(BridgeNumberGenerator numberGenerator, int size) {
        this.bridgeMaker = new BridgeMaker(numberGenerator);
        this.size = size;
        this.trialCount = 0;
        startNewGame();
        System.out.println(bridgeMaker.getList());
    }

    private void startNewGame() {
        bridgeMaker.makeBridge(size);
        bridgeStructure = bridgeMaker.getList();
        index = 0;
    }


    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean move(String nextPosition) {
        if (index >= size) {
            return false; // 게임 종료
        }

        boolean isCorrect = bridgeMaker.updateBridge(nextPosition, bridgeStructure.get(index));
        bridgeMaker.getBridge();
        if (!isCorrect) {
            return false; // 잘못된 선택, 게임 종료
        }

        index++;
        if (index == size) {
            System.out.println("축하합니다! 게임을 성공적으로 완료했습니다.");
            return false; // 게임 성공, 종료
        }

        return true; // 게임 계속
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public boolean retry() {
        System.out.println("오답입니다. 재시작하려면 R을 누르세요.");
        String restartInput = InputView.readRestart();
        if ("R".equalsIgnoreCase(restartInput)) {
            trialCount++;
            bridgeMaker.resetGameVisuals();
            startNewGame(); // 게임 재시작
            return true;
        }
        System.out.println("게임이 종료되었습니다. 총 시도 횟수: " + trialCount);
        return false;

    }
}