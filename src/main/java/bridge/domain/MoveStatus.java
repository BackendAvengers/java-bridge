package bridge.domain;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum MoveStatus {

    CORRECT("O"
            , (userDirection, upAndDownDirection) -> userDirection.equals(upAndDownDirection.getDirection())
            , (userDirection, bridgeDirection) -> userDirection.equals(bridgeDirection)),
    WRONG("X"
            , (userDirection, upAndDownDirection) -> userDirection.equals(upAndDownDirection.getDirection())
            , (userDirection, bridgeDirection) -> !userDirection.equals(bridgeDirection)),
    EMPTY(" "
            , (userDirection, upAndDownDirection) -> !userDirection.equals(upAndDownDirection.getDirection())
            , (userDirection, bridgeDirection) -> true);

	private final String status;
	private final BiPredicate<String, BridgeDirection> isMatchBridge;
	private final BiPredicate<String, String> isMatchDirection;

	MoveStatus(String status, BiPredicate<String, BridgeDirection> isMatchBridge,
			BiPredicate<String, String> isMatchDirection) {
		this.status = status;
		this.isMatchBridge = isMatchBridge;
		this.isMatchDirection = isMatchDirection;
	}
	
	public static String getStatus(String userDirection, BridgeDirection upAndDownDirection, String bridgeDirection) {
		return Arrays.stream(MoveStatus.values())
		.filter(status -> status.isMatchBridge.test(userDirection, upAndDownDirection))
		.filter(status -> status.isMatchDirection.test(userDirection, bridgeDirection))
		.findAny()
		.orElse(EMPTY)
		.status;
	}
	
	public String getStatusMessage() {
		return status;
	}
}