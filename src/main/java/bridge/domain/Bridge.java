package bridge.domain;

import java.util.List;

public class Bridge {

	private final List<String> bridge;

	public Bridge(List<String> bridge) {
		this.bridge = bridge;
	}

	public String getByIndex(int index) {
		return bridge.get(index);
	}
	
	public Integer size() {
		return bridge.size();
	}
}