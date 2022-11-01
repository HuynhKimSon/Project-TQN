package vn.com.irtech.irbot.business.dto;

import java.io.Serializable;
import java.util.List;

public class RobotSyncGslctReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long processId;
	private String syncId;
	private Integer serviceType;
	private String serviceName;
	private Boolean isCreate;
	private String voucherNo;
	private Boolean isChangeVoucherNo;
	private String oldVoucherNo;
	private String voucherDate;
	private String providerName;
	private String stockName;
	private String description;
	private Integer processNumber;
	private Integer importNumber;
	private Integer exportNumber;
	private Long nhapXuatId;

	private ProcessGslctConfig config;

	private List<RobotSyncGslctDetailReq> processOrder;

	private List<RobotSyncGslctDetailReq> importOrder;

	private List<RobotSyncGslctDetailReq> exportOrder;

	public Long getProcessId() {
		return processId;
	}

	public void setProcessId(Long processId) {
		this.processId = processId;
	}

	public String getSyncId() {
		return syncId;
	}

	public void setSyncId(String syncId) {
		this.syncId = syncId;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Boolean getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(Boolean isCreate) {
		this.isCreate = isCreate;
	}

	public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	public Boolean getIsChangeVoucherNo() {
		return isChangeVoucherNo;
	}

	public void setIsChangeVoucherNo(Boolean isChangeVoucherNo) {
		this.isChangeVoucherNo = isChangeVoucherNo;
	}

	public String getOldVoucherNo() {
		return oldVoucherNo;
	}

	public void setOldVoucherNo(String oldVoucherNo) {
		this.oldVoucherNo = oldVoucherNo;
	}

	public String getVoucherDate() {
		return voucherDate;
	}

	public void setVoucherDate(String voucherDate) {
		this.voucherDate = voucherDate;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(Integer processNumber) {
		this.processNumber = processNumber;
	}

	public ProcessGslctConfig getConfig() {
		return config;
	}

	public void setConfig(ProcessGslctConfig config) {
		this.config = config;
	}

	public List<RobotSyncGslctDetailReq> getProcessOrder() {
		return processOrder;
	}

	public void setProcessOrder(List<RobotSyncGslctDetailReq> processOrder) {
		this.processOrder = processOrder;
	}

	public List<RobotSyncGslctDetailReq> getImportOrder() {
		return importOrder;
	}

	public void setImportOrder(List<RobotSyncGslctDetailReq> importOrder) {
		this.importOrder = importOrder;
	}

	public List<RobotSyncGslctDetailReq> getExportOrder() {
		return exportOrder;
	}

	public void setExportOrder(List<RobotSyncGslctDetailReq> exportOrder) {
		this.exportOrder = exportOrder;
	}

	public Integer getImportNumber() {
		return importNumber;
	}

	public void setImportNumber(Integer importNumber) {
		this.importNumber = importNumber;
	}

	public Integer getExportNumber() {
		return exportNumber;
	}

	public void setExportNumber(Integer exportNumber) {
		this.exportNumber = exportNumber;
	}

	public Long getNhapXuatId() {
		return nhapXuatId;
	}

	public void setNhapXuatId(Long nhapXuatId) {
		this.nhapXuatId = nhapXuatId;
	}

}
