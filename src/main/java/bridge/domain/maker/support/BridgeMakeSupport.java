package bridge.domain.maker.support;

public enum BridgeMakeSupport {

    START("["),
    SEPARATOR("|"),
    END("]");

    private static final String FORMAT = "%s%s%s";

    private final String symbol;

    BridgeMakeSupport(String symbol) {
        this.symbol = symbol;
    }

    public static String createBridge(String status) {
        return String.format(FORMAT, START.symbol, status, END.symbol);
    }

    public static String createBridge(String preBridge, String status) {
        StringBuilder bridgeBuilder = new StringBuilder(preBridge);
        appendStatusToBridge(status, bridgeBuilder);
        return bridgeBuilder.toString();
    }

    private static void appendStatusToBridge(String status, StringBuilder bridgeBuilder) {
        bridgeBuilder.deleteCharAt(bridgeBuilder.length() - 1);
        bridgeBuilder.append(SEPARATOR.symbol);
        bridgeBuilder.append(status);
        bridgeBuilder.append(END.symbol);
    }

}
