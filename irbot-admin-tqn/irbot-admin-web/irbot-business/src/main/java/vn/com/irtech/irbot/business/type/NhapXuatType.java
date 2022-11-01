package vn.com.irtech.irbot.business.type;

public enum NhapXuatType {
	NHAP_THAN_NK_CTY_KHAC(1, "Nhập than NK – Từ cty khác"), 
	NHAP_THAN_SACH_CTY_KHAC(2, "Nhập than sạch – Từ cty khác"), 
	NHAP_THAN_SACH_NOI_BO(3, "Nhập than sạch – Nội bộ"), 
	XUAT_THAN_SACH_CTY_KHAC(4, "Xuất than sạch – Đến cty khác"), 
	XUAT_THAN_NK_NOI_BO(5, "Xuất than NK – Nội bộ"),
	XUAT_THAN_SACH_NOI_BO(6, "Xuất than sạch – Nội bộ"),
	PHIEU_PHA_TRON(7, "Phiếu pha trộn");

	private final Integer value;
	
	private final String title;

	NhapXuatType(Integer value, String title) {
		this.value = value;
		this.title = title;
	}

	public Integer value() {
		return this.value;
	}
	
	public String title() {
		return this.title;
	}

	public static NhapXuatType fromValue(Integer value) {
		for (NhapXuatType e : NhapXuatType.values()) {
			if (value == e.value) {
				return e;
			}
		}
		return null;
	}
}
