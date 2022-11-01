package vn.com.irtech.irbot.business.type;

public enum RobotServiceType {
	SYNC_NHAP_XUAT(100);

	private final Integer value;

	RobotServiceType(Integer value) {
		this.value = value;
	}

	public Integer value() {
		return this.value;
	}

	public static RobotServiceType fromValue(Integer value) {
		for (RobotServiceType e : RobotServiceType.values()) {
			if (value == e.value) {
				return e;
			}
		}
		return null;
	}
}
