package bridge.domain;

import bridge.domain.maker.BridgeMaker;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final BridgeMaker bridgeMaker;
    private int attemptCount = 1;
    private List<String> userSymbol = new ArrayList<>();
    private String lastBridge = "";

    public BridgeGame(BridgeMaker bridgeMaker) {
        this.bridgeMaker = bridgeMaker;
    }

    public List<String> makeBridge(int size) {
        return bridgeMaker.makeBridge(size);
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public String move(String bridge, String symbol) {
        userSymbol.add(symbol);

        if (isSuccessGame(bridge, symbol)) {
            String lastBridge = bridgeMaker.successBridge(bridge, userSymbol);
            this.lastBridge = lastBridge;
            return lastBridge;
        }
        String lastBridge = bridgeMaker.failBridge(bridge, userSymbol);
        this.lastBridge = lastBridge;
        return lastBridge;
    }

    public boolean isSuccessGame(String bridge, String symbol) {
        char checkSymbol = bridge.charAt(bridge.length() - 2);
        return symbol.equals(String.valueOf(checkSymbol));
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        attemptCount += 1;
        userSymbol = new ArrayList<>();
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public String getLastBridge() {
        return lastBridge;
    }
}