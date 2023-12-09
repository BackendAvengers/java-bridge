package bridge.domain;

import java.util.Arrays;

public enum GameRetry {

	RETRY("R"),
	QUIT("Q");

	private final String sign;

	GameRetry(String sign) {
		this.sign = sign;
	}

	public static GameRetry convertSignToGameRetry(String sign) {
		return Arrays.stream(GameRetry.values()).filter(retry -> retry.sign.equals(sign))
				.findAny()
				.get();
	}
	
	public static boolean checkRetry(String sing) {
		return sing.equals(GameRetry.RETRY.sign);
	}
}
