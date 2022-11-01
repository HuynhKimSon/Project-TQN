package vn.com.irtech.irbot.business.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import vn.com.irtech.core.common.domain.BaseEntity;

public class NhapXuat extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/** $column.columnComment */
	private Long id;

	private Integer syncType;

	private Integer type;

	private Integer mode;

	private String json;

	private Long voucherId;

	private String voucherNo;

	private String voucherNoGslct;

	private String voucherIndex;

	private String voucherType;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date voucherDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date voucherModifyDate;

	private String productGroup;

	private String providerId;

	private String providerName;

	private String stockId;

	private String reason;

	private String description;

	private Long importId;

	private String importNumberId;

	private Long exportId;

	private String exportNumberId;

	private Long processId;

	private Integer status;

	private String auto;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public Integer getSyncType() {
		return syncType;
	}

	public void setSyncType(Integer syncType) {
		this.syncType = syncType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getMode() {
		return mode;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getJson() {
		return json;
	}

	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}

	public Long getVoucherId() {
		return voucherId;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public String getVoucherNoGslct() {
		return voucherNoGslct;
	}

	public void setVoucherNoGslct(String voucherNoGslct) {
		this.voucherNoGslct = voucherNoGslct;
	}

	public void setVoucherIndex(String voucherIndex) {
		this.voucherIndex = voucherIndex;
	}

	public String getVoucherIndex() {
		return voucherIndex;
	}

	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}

	public String getVoucherType() {
		return voucherType;
	}

	public void setVoucherDate(Date voucherDate) {
		this.voucherDate = voucherDate;
	}

	public Date getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherModifyDate(Date voucherModifyDate) {
		this.voucherModifyDate = voucherModifyDate;
	}

	public Date getVoucherModifyDate() {
		return voucherModifyDate;
	}

	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}

	public String getProductGroup() {
		return productGroup;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockId() {
		return stockId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setImportId(Long importId) {
		this.importId = importId;
	}

	public Long getImportId() {
		return importId;
	}

	public void setImportNumberId(String importNumberId) {
		this.importNumberId = importNumberId;
	}

	public String getImportNumberId() {
		return importNumberId;
	}

	public void setExportId(Long exportId) {
		this.exportId = exportId;
	}

	public Long getExportId() {
		return exportId;
	}

	public void setExportNumberId(String exportNumberId) {
		this.exportNumberId = exportNumberId;
	}

	public String getExportNumberId() {
		return exportNumberId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public Long getProcessId() {
		return processId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

}