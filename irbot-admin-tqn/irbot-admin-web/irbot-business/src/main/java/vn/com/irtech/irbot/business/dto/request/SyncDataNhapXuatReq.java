package vn.com.irtech.irbot.business.dto.request;

import java.io.Serializable;

public class SyncDataNhapXuatReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private String fromDate;

	private String toDate;

//	private Boolean sendRobot;

	private Integer serviceType;

	private Boolean isAuto;

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	/*
	 * public Boolean getSendRobot() { return sendRobot; }
	 * 
	 * public void setSendRobot(Boolean sendRobot) { this.sendRobot = sendRobot; }
	 */

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Boolean getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}

}
