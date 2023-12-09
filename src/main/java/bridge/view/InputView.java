package bridge.view;

import bridge.io.ViewPrinter;
import bridge.io.ViewReader;

/**
 * 사용자로부터 입력을 받는 역할을 한다.
 */
public class InputView {
	private final ViewReader reader;
	private final ViewPrinter printer;

	public InputView() {
		this.reader = new ViewReader();
		this.printer = new ViewPrinter();
	}

	/**
	 * 다리의 길이를 입력받는다.
	 */
	public int inputBridgeSize() {
		try {
			printer.printLine("다리의 길이를 입력해주세요.");
			String inputValue = reader.readLine();

			return Integer.parseInt(inputValue);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return inputBridgeSize();
		}
	}

	/**
	 * 사용자가 이동할 칸을 입력받는다.
	 */
	public String inputMoveBlock() {
		try {
			printer.printLine("이동할 칸을 선택해주세요. (위: U, 아래: D)");
			String inputValue = reader.readLine();

			return inputValue;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return inputMoveBlock();
		}
	}

	/**
	 * 사용자가 게임을 다시 시도할지 종료할지 여부를 입력받는다.
	 */
	public String inputRetryCommand() {
		try {
			printer.printLine("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
			String inputValue = reader.readLine();

			return inputValue;
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return inputRetryCommand();
		}
	}
}