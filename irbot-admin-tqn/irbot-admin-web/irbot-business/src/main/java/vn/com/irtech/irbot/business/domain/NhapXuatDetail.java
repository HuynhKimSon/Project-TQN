package vn.com.irtech.irbot.business.domain;

import vn.com.irtech.core.common.domain.BaseEntity;

public class NhapXuatDetail extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** $column.columnComment */
	private Long id;

	private Long nhapXuatId;

	private Long voucherId;

	private Long detailId;

	private String productId;

	private String productName;

	private String stockId;

	private String unitId;

	private Double akRate;

	private Double wtpRate;

	private Double convertQuantity;

	private Double realQuantity;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getNhapXuatId() {
		return nhapXuatId;
	}

	public void setNhapXuatId(Long nhapXuatId) {
		this.nhapXuatId = nhapXuatId;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setAkRate(Double akRate) {
		this.akRate = akRate;
	}

	public Double getAkRate() {
		return akRate;
	}

	public void setWtpRate(Double wtpRate) {
		this.wtpRate = wtpRate;
	}

	public Double getWtpRate() {
		return wtpRate;
	}

	public void setConvertQuantity(Double convertQuantity) {
		this.convertQuantity = convertQuantity;
	}

	public Double getConvertQuantity() {
		return convertQuantity;
	}

	public void setRealQuantity(Double realQuantity) {
		this.realQuantity = realQuantity;
	}

	public Double getRealQuantity() {
		return realQuantity;
	}

}