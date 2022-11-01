package vn.com.irtech.irbot.business.dto.response;

import java.util.List;

import vn.com.irtech.irbot.business.dto.VoucherMaster;

public class ApiNhapXuatRes extends ApiRes {

	private static final long serialVersionUID = 1L;

	private List<VoucherMaster> data;

	public List<VoucherMaster> getData() {
		return data;
	}

	public void setData(List<VoucherMaster> data) {
		this.data = data;
	}

}
