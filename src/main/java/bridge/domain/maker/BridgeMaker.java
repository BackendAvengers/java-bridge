package bridge.domain.maker;

import bridge.domain.maker.support.BridgeMakeSupport;
import bridge.domain.maker.support.CrossingStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateBridgeSize(size);
        List<String> bridge = new ArrayList<>();

        String status = getStatus();
        bridge.add(BridgeMakeSupport.createBridge(status));
        for (int index = 1; index < size; index++) {
            String preBridge = bridge.get(index - 1);
            bridge.add(BridgeMakeSupport.createBridge(preBridge, getStatus()));
        }

        return Collections.unmodifiableList(bridge);
    }

    private static void validateBridgeSize(int size) {
        if (size < 3 || size > 20) {
            throw new IllegalArgumentException("[ERROR] 다리 길이는 3부터 20 사이의 숫자여야 합니다.");
        }
    }

    private String getStatus() {
        return CrossingStatus.getBridgeSymbol(bridgeNumberGenerator.generate());
    }

}