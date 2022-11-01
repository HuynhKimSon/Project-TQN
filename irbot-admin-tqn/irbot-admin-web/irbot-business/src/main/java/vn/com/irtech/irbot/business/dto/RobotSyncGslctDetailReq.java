package vn.com.irtech.irbot.business.dto;

import java.io.Serializable;

public class RobotSyncGslctDetailReq implements Serializable {
	private static final long serialVersionUID = 1L;

	private String productName;

	private String realQuantity;

	private String akRate;

	private String wtpRate;

	private String convertQuantity;

	private String stockName;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRealQuantity() {
		return realQuantity;
	}

	public void setRealQuantity(String realQuantity) {
		this.realQuantity = realQuantity;
	}

	public String getAkRate() {
		return akRate;
	}

	public void setAkRate(String akRate) {
		this.akRate = akRate;
	}

	public String getWtpRate() {
		return wtpRate;
	}

	public void setWtpRate(String wtpRate) {
		this.wtpRate = wtpRate;
	}

	public String getConvertQuantity() {
		return convertQuantity;
	}

	public void setConvertQuantity(String convertQuantity) {
		this.convertQuantity = convertQuantity;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
