package bridge.domain.maker.support;

import bridge.domain.maker.constant.BridgeMakerErrorMessage;

import java.util.Arrays;

public enum CrossingStatus {

    UP("U", 1),
    DOWN("D", 0);

    public static final String SUCCESS_SYMBOL = " O ";
    public static final String FAIL_SYMBOL = " X ";
    public static final String PASS_SYMBOL = "   ";
    private final String symbol;
    private final int value;

    CrossingStatus(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public static String getBridgeSymbol(int value) {
        return Arrays.stream(CrossingStatus.values())
                .filter(s -> s.value == value)
                .map(s -> s.symbol)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(BridgeMakerErrorMessage.INVALID_BRIDGE_VALUE.getMessage()));
    }

    public static boolean isUpSymbol(String symbol) {
        return UP.symbol.equals(symbol);
    }

    public String getSymbol() {
        return symbol;
    }
}
