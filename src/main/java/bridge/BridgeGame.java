package bridge;

import java.util.Objects;

import bridge.domain.Bridge;
import bridge.domain.BridgePrinter;
import bridge.domain.GameResult;
import bridge.domain.GameRetry;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

	private static final int DEFAULT_LOCATION = 0;
	private static final int DEFAULT_TRYCOUNT = 1;
	private static final GameResult DEFAULT_RESULT = GameResult.CONTINUE;

	private final Bridge bridge;
	private final BridgePrinter printer;
	private int location;
	private int tryCount;
	private GameResult result;

	public BridgeGame(Bridge bridge) {
		this.bridge = bridge;
		this.printer = new BridgePrinter();
		this.location = DEFAULT_LOCATION;
		this.tryCount = DEFAULT_TRYCOUNT;
		this.result = DEFAULT_RESULT;
	}

	/**
	 * 사용자가 칸을 이동할 때 사용하는 메서드
	 * <p>
	 * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void move(String inputDirection) {
		printer.addMessage(inputDirection, bridge.getByIndex(location));
		if (!bridge.getByIndex(location).equals(inputDirection)) {
			result = GameResult.FAIL;
		}

		if (bridge.getByIndex(location).equals(inputDirection)) {
			location++;
			checkEnd();
		}

	}

	private void checkEnd() {
		if (bridge.size() == location) {
			result = GameResult.SUCCESS;
		}

		if (!printer.isContinueGame()) {
			result = GameResult.FAIL;
		}
	}

	/**
	 * 사용자가 게임을 다시 시도할 때 사용하는 메서드
	 * <p>
	 * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void retry() {
		location = DEFAULT_LOCATION;
		result = DEFAULT_RESULT;
		printer.initialize();

		tryCount++;
	}

	public boolean isContinue() {
		return result.equals(GameResult.CONTINUE);
	}

	public boolean isFail() {
		return result.equals(GameResult.FAIL);
	}

	public void updateRestart(String restart) {
		if (GameRetry.checkRetry(restart)) {
			result = GameResult.CONTINUE;
			retry();
		}
	}

	public String getBridgePrintMaker() {
		return printer.getMessage();
	}

	public Integer getTryCount() {
		return tryCount;
	}

	public String getResultMessage() {
		return result.getStatusMessage();
	}
}