package bridge.domain.maker;

import bridge.domain.maker.constant.BridgeMakerErrorMessage;
import bridge.domain.maker.support.BridgeMakeSupport;
import bridge.domain.maker.support.CrossingStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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

    private void validateBridgeSize(int size) {
        if (size < 3 || size > 20) {
            throw new IllegalArgumentException(BridgeMakerErrorMessage.INVALID_BRIDGE_SIZE.getMessage());
        }
    }

    private String getStatus() {
        return CrossingStatus.getBridgeSymbol(bridgeNumberGenerator.generate());
    }

    public String successBridge(String bridge, List<String> userSymbol) {
        List<String> bridgeSplit = getBridgeSplit(bridge);
        StringBuilder upBridgeBuilder = new StringBuilder();
        StringBuilder downBridgeBuilder = new StringBuilder();

        updateBridgeBuilder(userSymbol, bridgeSplit, upBridgeBuilder, downBridgeBuilder);
        deleteLastSeparator(upBridgeBuilder, downBridgeBuilder);
        return mergeBridge(upBridgeBuilder.toString(), downBridgeBuilder.toString());
    }

    private List<String> getBridgeSplit(String bridge) {
        return Arrays.asList(bridge.split("\\|"));
    }

    private void updateBridgeBuilder(List<String> userSymbol, List<String> bridgeSplit, StringBuilder upBridgeBuilder,
                                     StringBuilder downBridgeBuilder) {
        IntStream.range(0, bridgeSplit.size())
                .forEach(i -> {
                    String symbol = userSymbol.get(i);
                    String unitBridge = bridgeSplit.get(i);

                    if (CrossingStatus.UP.getSymbol().equals(symbol)) {
                        appendBridgeBuilder(upBridgeBuilder, symbol, unitBridge, CrossingStatus.SUCCESS_SYMBOL);
                        appendBridgeBuilder(downBridgeBuilder, symbol, unitBridge, CrossingStatus.PASS_SYMBOL);
                    }
                    if (CrossingStatus.DOWN.getSymbol().equals(symbol)){
                        appendBridgeBuilder(upBridgeBuilder, symbol, unitBridge, CrossingStatus.PASS_SYMBOL);
                        appendBridgeBuilder(downBridgeBuilder, symbol, unitBridge, CrossingStatus.SUCCESS_SYMBOL);
                    }
                });
    }

    private void deleteLastSeparator(StringBuilder upBridgeBuilder, StringBuilder downBridgeBuilder) {
        upBridgeBuilder.deleteCharAt(upBridgeBuilder.length() - 1);
        downBridgeBuilder.deleteCharAt(downBridgeBuilder.length() - 1);
    }

    private void appendBridgeBuilder(StringBuilder bridgeBuilder, String symbol,
                                            String unitBridge, String resultSymbol) {
        bridgeBuilder.append(unitBridge.replace(symbol, resultSymbol))
                .append(BridgeMakeSupport.SEPARATOR.getSymbol());
    }


    private String mergeBridge(String upBridge, String downBridge) {
        return new StringBuilder(upBridge)
                .append(System.lineSeparator())
                .append(downBridge)
                .toString();
    }

    public String failBridge(String bridge, List<String> userSymbol) {
        List<String> bridgeSplit = getBridgeSplit(bridge);
        StringBuilder upBridgeBuilder = new StringBuilder();
        StringBuilder downBridgeBuilder = new StringBuilder();

        updateBridgeBuilder(userSymbol, bridgeSplit, upBridgeBuilder, downBridgeBuilder);
        String userLastSymbol = userSymbol.get(userSymbol.size() - 1);
        return failBridgeMerge(upBridgeBuilder.toString(), downBridgeBuilder.toString(), userLastSymbol);
    }

    private String failBridgeMerge(String upBridge, String downBridge, String userLastSymbol) {
        if (CrossingStatus.UP.equals(userLastSymbol)) {
            return mergeBridge(
                    getBridgeReplace(upBridge, CrossingStatus.FAIL_SYMBOL),
                    getBridgeReplace(downBridge, CrossingStatus.PASS_SYMBOL));
        }
        return mergeBridge(
                getBridgeReplace(upBridge, CrossingStatus.PASS_SYMBOL),
                getBridgeReplace(downBridge, CrossingStatus.FAIL_SYMBOL));
    }

    private String getBridgeReplace(String bridge, String status) {
        return new StringBuilder(bridge)
                .delete(bridge.length() - 3, bridge.length())
                .append(status)
                .append(BridgeMakeSupport.END.getSymbol())
                .toString();
    }

}