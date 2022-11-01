package vn.com.irtech.irbot.business.dto.response;

import java.util.List;

import vn.com.irtech.irbot.business.dto.DismantionMaster;

public class ApiPhaTronRes extends ApiRes {

	private static final long serialVersionUID = 1L;

	private List<DismantionMaster> data;

	public List<DismantionMaster> getData() {
		return data;
	}

	public void setData(List<DismantionMaster> data) {
		this.data = data;
	}

}
