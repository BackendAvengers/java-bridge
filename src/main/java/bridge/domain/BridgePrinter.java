package bridge.domain;

import java.util.StringJoiner;

public class BridgePrinter {

	private static final String PREFIX = "[ ";
	private static final String SUFFIX = " ]";
	private static final String DELIMITER = " | ";
	private static final String NEW_LINE = System.lineSeparator();

	private StringJoiner upBridgeMessage;
	private StringJoiner downBridgeMessage;
	private boolean isContinue;

	public BridgePrinter() {
		this.upBridgeMessage = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
		this.downBridgeMessage = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
		this.isContinue = true;
	}

	public void addMessage(String inputDirection, String bridgeDirection) {
		String upMessage = MoveStatus.getStatus(inputDirection, BridgeDirection.UP, bridgeDirection);
		String downMessage = MoveStatus.getStatus(inputDirection, BridgeDirection.DOWN, bridgeDirection);

		upBridgeMessage.add(upMessage);
		downBridgeMessage.add(downMessage);
		updateContinueGame(upMessage, downMessage);
	}

	public void initialize() {
		this.upBridgeMessage = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
		this.downBridgeMessage = new StringJoiner(DELIMITER, PREFIX, SUFFIX);
		this.isContinue = true;
	}

	public String getMessage() {
		return String.join(NEW_LINE, upBridgeMessage.toString(), downBridgeMessage.toString());
	}

	public boolean isContinueGame() {
		return isContinue;
	}

	private void updateContinueGame(String upMoveResult, String downMoveResult) {
		if (upMoveResult.equals(MoveStatus.WRONG.getStatusMessage())
				|| downMoveResult.equals(MoveStatus.WRONG.getStatusMessage())) {
			isContinue = false;
		}
	}
}
