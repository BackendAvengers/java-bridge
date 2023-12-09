package bridge.domain;

import java.util.Arrays;

public enum BridgeDirection {

	UP(1, "U"),
	DOWN(0, "D");

	private final int value;
	private final String direction;

	BridgeDirection(int value, String direction) {
		this.value = value;
		this.direction = direction;
	}
	
	public static String convertValuetoDirection(int value) {
		return Arrays.stream(BridgeDirection.values())
				.filter(bridgeDirection -> bridgeDirection.value == value)
				.findAny()
				.get()
				.direction;
	}

	public String getDirection() {
		return direction;
	}
}
