package bridge.io;

import camp.nextstep.edu.missionutils.Console;

public final class ViewReader implements Reader {

	@Override
	public String readLine() {
		return Console.readLine();
	}
}