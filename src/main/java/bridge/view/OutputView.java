package bridge.view;

import bridge.BridgeGame;
import bridge.io.ViewPrinter;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {
	private static final String RESULT_STATUS_MESSAGE = "게임 성공 여부: %s";
	private static final String TRY_MESSAGE = "총 시도한 횟수: %d";
	private static final String NEW_LINE = System.lineSeparator();

	private final ViewPrinter printer;

	public OutputView() {
		this.printer = new ViewPrinter();
	}

	public void printStart() {
		printer.printLine("다리 건너기 게임을 시작합니다." + NEW_LINE);
	}

	/**
	 * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void printMap(String bridgePrint) {
		printer.printLine(bridgePrint);
	}

	/**
	 * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
	 * <p>
	 * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
	 */
	public void printResult(BridgeGame bridgeGame) {
		printer.printLine("최종 게임 결과");
		printer.printLine(bridgeGame.getBridgePrintMaker());
		printer.printLine(String.format("게임 성공 여부: %s", bridgeGame.getResultMessage()));
		printer.printLine(String.format("총 시도한 횟수: %d", bridgeGame.getTryCount()));
	}
}