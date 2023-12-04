package bridge;

import java.util.ArrayList;
import java.util.List;

public class BridgeMaker {
    private final BridgeNumberGenerator bridgeNumberGenerator;
    private List<Integer> validBridgeLocation = new ArrayList<>();
    private List<String> upperBridge = new ArrayList<>();
    private List<String> lowerBridge = new ArrayList<>();

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    public void resetGameVisuals() {
        upperBridge.clear();
        lowerBridge.clear();
    }


    public void makeBridge(int size) {
        for (int i = 0; i < size; i++) {
            validBridgeLocation.add(bridgeNumberGenerator.generate());
        }
    }

    public boolean updateBridge(String nextPosition, int validPositionIndex) {
        String nextValidPosition = "D"; // 기본값으로 "D"를 설정
        if (validBridgeLocation.get(validPositionIndex) == 1) {
            nextValidPosition = "U"; // 조건이 참이면 "U"로 변경
        }
        if (nextPosition.equals(nextValidPosition)) {
            updateCorrectBridge(nextPosition);
            return true;
        }
        updateWrongBridge(nextPosition);
        return false;
    }

    private void updateCorrectBridge(String position) {
        if ("U".equals(position)) {
            upperBridge.add(" O ");
            lowerBridge.add("   ");
        }
        if ("D".equals(position)) {
            lowerBridge.add(" O ");
            upperBridge.add("   ");
        }
    }

    private void updateWrongBridge(String position) {
        if ("U".equals(position)) {
            upperBridge.add(" X ");
            lowerBridge.add("   ");
        }
        if ("D".equals(position)) {
            lowerBridge.add(" X ");
            upperBridge.add("   ");
        }
    }

    public void getBridge() {
        System.out.println(stringifyBridge(upperBridge));
        System.out.println(stringifyBridge(lowerBridge));
    }

    private String stringifyBridge(List<String> bridge) {
        return "[ " + String.join(" | ", bridge) + " ]";
    }

    public List<Integer> getList() {
        return validBridgeLocation;
    }
}
