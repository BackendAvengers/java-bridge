package bridge.io;

public final class ViewPrinter implements Printer {

	@Override
	public void printLine(String message) {
		System.out.println(message);
	}
}