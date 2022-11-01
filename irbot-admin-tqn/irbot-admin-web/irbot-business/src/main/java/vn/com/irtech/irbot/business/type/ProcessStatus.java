package vn.com.irtech.irbot.business.type;

public enum ProcessStatus {
	NOTSEND(0), SENT(1),  PROCESSING(2), FAIL(3), SUCCESS(4);

	private final Integer value;

	ProcessStatus(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return this.value;
	}

	public static ProcessStatus fromValue(Integer value) {
		for (ProcessStatus e : ProcessStatus.values()) {
			if (value == e.value) {
				return e;
			}
		}
		return null;
	}
}
