package vn.com.irtech.irbot.business.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import vn.com.irtech.core.common.domain.entity.SysDictData;
import vn.com.irtech.core.common.text.Convert;
import vn.com.irtech.core.common.utils.DateUtils;
import vn.com.irtech.core.common.utils.JsonUtils;
import vn.com.irtech.core.common.utils.StringUtils;
import vn.com.irtech.core.system.service.ISysConfigService;
import vn.com.irtech.core.system.service.ISysDictDataService;
import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.NhapXuatDetail;
import vn.com.irtech.irbot.business.domain.Robot;
import vn.com.irtech.irbot.business.domain.WorkProcess;
import vn.com.irtech.irbot.business.dto.DismantionMaster;
import vn.com.irtech.irbot.business.dto.ProcessGslctConfig;
import vn.com.irtech.irbot.business.dto.RobotSyncGslctDetailReq;
import vn.com.irtech.irbot.business.dto.RobotSyncGslctReq;
import vn.com.irtech.irbot.business.dto.VoucherDetail;
import vn.com.irtech.irbot.business.dto.VoucherMaster;
import vn.com.irtech.irbot.business.dto.request.ApiGetNhapXuatDataReq;
import vn.com.irtech.irbot.business.dto.request.SyncDataNhapXuatReq;
import vn.com.irtech.irbot.business.mapper.NhapXuatDetailMapper;
import vn.com.irtech.irbot.business.mapper.NhapXuatMapper;
import vn.com.irtech.irbot.business.mapper.RobotMapper;
import vn.com.irtech.irbot.business.mapper.WorkProcessMapper;
import vn.com.irtech.irbot.business.mqtt.MqttService;
import vn.com.irtech.irbot.business.service.IApiGatewayService;
import vn.com.irtech.irbot.business.service.INhapXuatService;
import vn.com.irtech.irbot.business.type.NhapXuatType;
import vn.com.irtech.irbot.business.type.ProcessMode;
import vn.com.irtech.irbot.business.type.ProcessStatus;
import vn.com.irtech.irbot.business.type.RobotServiceType;
import vn.com.irtech.irbot.business.type.RobotStatusType;
import vn.com.irtech.irbot.business.type.SyncType;

@Service
public class NhapXuatServiceImpl implements INhapXuatService {

	private static final Logger logger = LoggerFactory.getLogger(NhapXuatServiceImpl.class);

	final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Autowired
	private NhapXuatMapper nhapXuatMapper;

	@Autowired
	private NhapXuatDetailMapper nhapXuatDetailMapper;

	@Autowired
	private RobotMapper robotMapper;

	@Autowired
	private IApiGatewayService apiGatewayService;

	@Autowired
	private WorkProcessMapper workProcessMapper;

	@Autowired
	private ISysConfigService configService;

	@Autowired
	private MqttService mqttService;

	@Autowired
	private ISysDictDataService dictDataService;

	/**
	 * Query nhapXuat
	 *
	 * @param id ID nhapXuat
	 * @return nhapXuat
	 */
	@Override
	public NhapXuat selectNhapXuatById(Long id) {
		return nhapXuatMapper.selectNhapXuatById(id);
	}

	/**
	 * Query list nhapXuat
	 *
	 * @param nhapXuat nhapXuat
	 * @return nhapXuat
	 */
	@Override
	public List<NhapXuat> selectNhapXuatList(NhapXuat nhapXuat) {
		return nhapXuatMapper.selectNhapXuatList(nhapXuat);
	}

	/**
	 * Added nhapXuat
	 *
	 * @param nhapXuat nhapXuat
	 * @return result
	 */
	@Override
	public int insertNhapXuat(NhapXuat nhapXuat) {
		return nhapXuatMapper.insertNhapXuat(nhapXuat);
	}

	/**
	 * Update nhapXuat
	 *
	 * @param nhapXuat nhapXuat
	 * @return result
	 */
	@Override
	public int updateNhapXuat(NhapXuat nhapXuat) {
		return nhapXuatMapper.updateNhapXuat(nhapXuat);
	}

	/**
	 * Delete object nhapXuat
	 *
	 * @param id ID of the data to be deleted
	 * @return result
	 */
	@Override
	@Transactional
	public int deleteNhapXuatByIds(String ids) {
		String idArr[] = Convert.toStrArray(ids);

		// Xoa detail
		nhapXuatDetailMapper.deleteNhapXuatDetailByNhapXuatIds(idArr);

		return nhapXuatMapper.deleteNhapXuatByIds(idArr);
	}

	/**
	 * Delete information nhapXuat
	 *
	 * @param id ID nhapXuat
	 * @return result
	 */
	@Override
	@Transactional
	public int deleteNhapXuatById(Long id) {

		nhapXuatDetailMapper.deleteNhapXuatDetailByNhapXuatIds(new String[] { id.toString() });

		return nhapXuatMapper.deleteNhapXuatById(id);
	}

	@Override
	public int sync(SyncDataNhapXuatReq request) throws Exception {
		int result = 0;
		logger.info("Sync Nhap Xuat - Start !");
		/*
		 * // Check robot available boolean sendRobot = request.getSendRobot(); if
		 * (sendRobot == true) { // Find robot available List<Robot> robots =
		 * robotMapper.selectRobotByService(RobotServiceType.SYNC_NHAP_XUAT.value(),
		 * RobotStatusType.AVAILABLE.value());
		 * 
		 * // Case if have not any robot available if (CollectionUtils.isEmpty(robots))
		 * { logger.info("Không tìm thấy robot khả dụng để làm lệnh!");
		 * logger.info("Sync Nhap Xuat - Finish !"); throw new
		 * Exception("Không tìm thấy robot khả dụng để làm lệnh!"); } }
		 */
		try {

			Date fromDate = DateUtils.parseDate(request.getFromDate() + " 00:00:00", DATE_FORMAT);
			Date toDate = DateUtils.parseDate(request.getToDate() + " 23:59:59", DATE_FORMAT);
			Date now = DateUtils.addSeconds(new Date(), -30);
			if (toDate.after(now)) {
				toDate = now;
			}

			if (fromDate.after(toDate)) {
				return 0;
			}

			ApiGetNhapXuatDataReq apiRequest = new ApiGetNhapXuatDataReq();
			apiRequest.setStartDate(DateUtils.parseDateToStr(DATE_FORMAT, fromDate));
			apiRequest.setEndDate(DateUtils.parseDateToStr(DATE_FORMAT, toDate));

			// Lay du lieu nhap kho
			if (request.getServiceType() == null || SyncType.NHAP.value() == request.getServiceType()) {
				result += getImportData(apiRequest, request.getIsAuto());
			}

			// Lay du lieu xuat kho
			if (request.getServiceType() == null || SyncType.XUAT.value() == request.getServiceType()) {
				result += getExportData(apiRequest, request.getIsAuto());
			}

			// Lay du lieu pha tron
			if (request.getServiceType() == null || SyncType.PHA_TRON.value() == request.getServiceType()) {
				result += getDismantionData(apiRequest, request.getIsAuto());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error(">>>>>> Error: " + e.getMessage());
			throw e;
		} finally {
			logger.info("Sync GSLCT - Finish !");
		}

		return result;
	}

	private int getImportData(ApiGetNhapXuatDataReq request, Boolean isAuto) throws Exception {
		int rowSuccess = 0;
		List<VoucherMaster> importList = apiGatewayService.getImportData(request);
		logger.info(">> Tong phieu nhap: " + (importList == null ? 0 : importList.size()));
		if (CollectionUtils.isNotEmpty(importList)) {
			for (VoucherMaster importVoucher : importList) {
				NhapXuatType type = getNhapXuatType(importVoucher.getVoucherType(), importVoucher.getProductGroup());
				if (type == null) {
					logger.info(">> Phieu khong hop le: " + importVoucher.getVoucherNo() + " - "
							+ importVoucher.getVoucherId());
					continue;
				}

				// Kiểm tra ton tai
				NhapXuat nhapXuatSelect = new NhapXuat();
				nhapXuatSelect.setVoucherId(importVoucher.getVoucherId());
				nhapXuatSelect.setSyncType(SyncType.NHAP.value());
				List<NhapXuat> nhapXuatExistList = nhapXuatMapper.selectNhapXuatList(nhapXuatSelect);
				if (CollectionUtils.isNotEmpty(nhapXuatExistList)) {
					// Truonng hop da ton tai - update phieu
					// Kiem tra du lieu co thay doi
					String jsonCompare = JsonUtils.objToJson(importVoucher);
					NhapXuat nhapXuatExist = nhapXuatExistList.get(0);
					if (StringUtils.compare(jsonCompare, nhapXuatExist.getJson()) != 0) {
						insertNhapXuat(importVoucher, ProcessMode.UPDATE.value(), isAuto, type, SyncType.NHAP);
					}
				} else {
					// Truong hop chua ton tai - insert phieu
					insertNhapXuat(importVoucher, ProcessMode.INSERT.value(), isAuto, type, SyncType.NHAP);
					rowSuccess++;
				}
			}
		}
		return rowSuccess;
	}

	private int getExportData(ApiGetNhapXuatDataReq request, Boolean isAuto) throws Exception {
		int rowSuccess = 0;
		List<VoucherMaster> exportList = apiGatewayService.getExportData(request);
		logger.info(">> Tong phieu xuat: " + (exportList == null ? 0 : exportList.size()));
		if (CollectionUtils.isNotEmpty(exportList)) {
			for (VoucherMaster exportVoucher : exportList) {
				NhapXuatType type = getNhapXuatType(exportVoucher.getVoucherType(), exportVoucher.getProductGroup());
				if (type == null) {
					logger.info(">> Phieu khong hop le: " + exportVoucher.getVoucherNo() + " - "
							+ exportVoucher.getVoucherId());
					continue;
				}

				// Kiểm tra ton tai
				NhapXuat nhapXuatSelect = new NhapXuat();
				nhapXuatSelect.setVoucherId(exportVoucher.getVoucherId());
				nhapXuatSelect.setSyncType(SyncType.XUAT.value());
				List<NhapXuat> nhapXuatExistList = nhapXuatMapper.selectNhapXuatList(nhapXuatSelect);
				if (CollectionUtils.isNotEmpty(nhapXuatExistList)) {
					// Truonng hop da ton tai - update phieu
					// Kiem tra du lieu co thay doi
					String jsonCompare = JsonUtils.objToJson(exportVoucher);
					NhapXuat nhapXuatExist = nhapXuatExistList.get(0);
					if (StringUtils.compare(jsonCompare, nhapXuatExist.getJson()) != 0) {
						insertNhapXuat(exportVoucher, ProcessMode.UPDATE.value(), isAuto, type, SyncType.XUAT);
					}
				} else {
					// Truong hop chua ton tai - insert phieu
					insertNhapXuat(exportVoucher, ProcessMode.INSERT.value(), isAuto, type, SyncType.XUAT);
					rowSuccess++;
				}
			}
		}
		return rowSuccess;
	}

	private int getDismantionData(ApiGetNhapXuatDataReq request, Boolean isAuto) throws Exception {
		int rowSuccess = 0;
		List<DismantionMaster> dismantionList = apiGatewayService.getDismantionData(request);
		logger.info(">> Tong phieu pha tron: " + (dismantionList == null ? 0 : dismantionList.size()));
		if (CollectionUtils.isNotEmpty(dismantionList)) {
			for (DismantionMaster dismantion : dismantionList) {
				NhapXuatType type = getNhapXuatType(dismantion.getVoucherType(), dismantion.getProductGroup());
				if (type == null) {
					logger.info(
							">> Phieu khong hop le: " + dismantion.getVoucherNo() + " - " + dismantion.getVoucherId());
					continue;
				}

				// Kiểm tra ton tai
				NhapXuat nhapXuatSelect = new NhapXuat();
				nhapXuatSelect.setVoucherId(dismantion.getVoucherId());
				nhapXuatSelect.setSyncType(SyncType.PHA_TRON.value());
				List<NhapXuat> nhapXuatExistList = nhapXuatMapper.selectNhapXuatList(nhapXuatSelect);
				if (CollectionUtils.isNotEmpty(nhapXuatExistList)) {
					// Truonng hop da ton tai - update phieu
					// Kiem tra du lieu co thay doi
					String jsonCompare = JsonUtils.objToJson(dismantion);
					NhapXuat nhapXuatExist = nhapXuatExistList.get(0);
					if (StringUtils.compare(jsonCompare, nhapXuatExist.getJson()) != 0) {
						insertNhapXuat(dismantion, ProcessMode.UPDATE.value(), isAuto, type, SyncType.PHA_TRON);
					}
				} else {
					// Truong hop chua ton tai - insert phieu
					insertNhapXuat(dismantion, ProcessMode.INSERT.value(), isAuto, type, SyncType.PHA_TRON);
					rowSuccess++;
				}
			}
		}
		return rowSuccess;
	}

	@Transactional
	private void insertNhapXuat(VoucherMaster voucher, int mode, Boolean isAuto, NhapXuatType type, SyncType syncType) {
		// Insert master data
		NhapXuat nhapXuatInsert = new NhapXuat();
		nhapXuatInsert.setMode(mode);
		nhapXuatInsert.setJson(JsonUtils.objToJson(voucher));
		nhapXuatInsert.setSyncType(syncType.value());
		nhapXuatInsert.setType(type.value());
		nhapXuatInsert.setVoucherId(voucher.getVoucherId());
		nhapXuatInsert.setVoucherNo(voucher.getVoucherNo());
		Date now = new Date();
		String voucherNoGslct = getStockId(voucher.getStockId()) + "." + voucher.getVoucherType() + "."
				+ DateUtils.parseDateToStr("yyyy", now) + "." + voucher.getVoucherIndex();
		nhapXuatInsert.setVoucherNoGslct(voucherNoGslct);
		nhapXuatInsert.setVoucherIndex(voucher.getVoucherIndex());
		nhapXuatInsert.setVoucherType(voucher.getVoucherType());
		nhapXuatInsert.setVoucherDate(voucher.getVoucherDate());
		nhapXuatInsert.setVoucherModifyDate(voucher.getVoucherModifyDate());
		nhapXuatInsert.setProductGroup(voucher.getProductGroup());
		nhapXuatInsert.setProviderId(voucher.getProviderId());
		nhapXuatInsert.setProviderName(voucher.getProviderName());
		nhapXuatInsert.setStockId(voucher.getStockId());
		nhapXuatInsert.setReason(voucher.getReason());
		nhapXuatInsert.setDescription(voucher.getDescription());
		nhapXuatInsert.setStatus(ProcessStatus.NOTSEND.value());
		isAuto = (isAuto == null) ? false : isAuto;
		nhapXuatInsert.setAuto(isAuto ? "1" : "0");
		nhapXuatMapper.insertNhapXuat(nhapXuatInsert);

		// Insert detail data
		if (CollectionUtils.isNotEmpty(voucher.getDetails())) {
			for (VoucherDetail detail : voucher.getDetails()) {
				insertNhapXuatDetail(detail, nhapXuatInsert.getId(), voucher.getVoucherId());
			}
		}
	}

	@Transactional
	private void insertNhapXuat(DismantionMaster dismantion, int mode, Boolean isAuto, NhapXuatType type, SyncType syncType) {
		// Insert master data
		NhapXuat nhapXuatInsert = new NhapXuat();
		nhapXuatInsert.setMode(mode);
		nhapXuatInsert.setJson(JsonUtils.objToJson(dismantion));
		nhapXuatInsert.setSyncType(syncType.value());
		nhapXuatInsert.setType(type.value());
		nhapXuatInsert.setVoucherId(dismantion.getVoucherId());
		nhapXuatInsert.setVoucherNo(dismantion.getVoucherNo());
		Date now = new Date();
		String voucherNoGslct = getStockId(dismantion.getStockId()) + "." + dismantion.getVoucherType() + "."
				+ DateUtils.parseDateToStr("yyyy", now) + "." + dismantion.getVoucherIndex();
		nhapXuatInsert.setVoucherNoGslct(voucherNoGslct);
		nhapXuatInsert.setVoucherIndex(dismantion.getVoucherIndex());
		nhapXuatInsert.setVoucherType(dismantion.getVoucherType());
		nhapXuatInsert.setVoucherDate(dismantion.getVoucherDate());
		nhapXuatInsert.setVoucherModifyDate(dismantion.getVoucherModifyDate());
		nhapXuatInsert.setProductGroup(dismantion.getProductGroup());
		nhapXuatInsert.setProviderId(dismantion.getProviderId());
		nhapXuatInsert.setProviderName(dismantion.getProviderName());
		nhapXuatInsert.setStockId(dismantion.getStockId());
		nhapXuatInsert.setReason(dismantion.getReason());
		nhapXuatInsert.setDescription(dismantion.getDescription());
		nhapXuatInsert.setImportId(dismantion.getImportId());
		nhapXuatInsert.setImportNumberId(dismantion.getImportNumberId());
		nhapXuatInsert.setExportId(dismantion.getExportId());
		nhapXuatInsert.setExportNumberId(dismantion.getExportNumberId());
		nhapXuatInsert.setStatus(ProcessStatus.NOTSEND.value());
		isAuto = (isAuto == null) ? false : isAuto;
		nhapXuatInsert.setAuto(isAuto ? "1" : "0");
		nhapXuatMapper.insertNhapXuat(nhapXuatInsert);

		// Insert detail data
		if (dismantion.getImportVoucher() != null
				&& CollectionUtils.isNotEmpty(dismantion.getImportVoucher().getDetails())) {
			for (VoucherDetail detail : dismantion.getImportVoucher().getDetails()) {
				insertNhapXuatDetail(detail, nhapXuatInsert.getId(), dismantion.getImportId());
			}
		}

		if (dismantion.getExportVoucher() != null
				&& CollectionUtils.isNotEmpty(dismantion.getExportVoucher().getDetails())) {
			for (VoucherDetail detail : dismantion.getExportVoucher().getDetails()) {
				insertNhapXuatDetail(detail, nhapXuatInsert.getId(), dismantion.getExportId());
			}
		}
	}

	private void insertNhapXuatDetail(VoucherDetail detail, long nhapXuatId, long voucherId) {
		NhapXuatDetail nhapXuatDetailInsert = new NhapXuatDetail();
		nhapXuatDetailInsert.setNhapXuatId(nhapXuatId);
		nhapXuatDetailInsert.setVoucherId(voucherId);
		nhapXuatDetailInsert.setDetailId(detail.getDetailId());
		nhapXuatDetailInsert.setProductId(detail.getProductId());
		nhapXuatDetailInsert.setProductName(detail.getProductName());
		nhapXuatDetailInsert.setStockId(detail.getStockId());
		nhapXuatDetailInsert.setUnitId(detail.getUnitId());
		nhapXuatDetailInsert.setAkRate(detail.getAkRate());
		nhapXuatDetailInsert.setWtpRate(detail.getWtpRate());
		nhapXuatDetailInsert.setConvertQuantity(detail.getConvertQuantity());
		nhapXuatDetailInsert.setRealQuantity(detail.getRealQuantity());
		nhapXuatDetailMapper.insertNhapXuatDetail(nhapXuatDetailInsert);
	}

	private NhapXuatType getNhapXuatType(String voucherType, String productGroup) {
		if (StringUtils.isBlank(voucherType)) {
			return null;
		}

		// VoucherType = 'PT" - serviceType = 7
		if ("PT".equalsIgnoreCase(voucherType)) {
			return NhapXuatType.PHIEU_PHA_TRON;
		}

		if (StringUtils.isBlank(productGroup)) {
			return null;
		}

		// Case 1: VoucherType = 'NM' & ProductGroup = 'ThanNguyenKhai' - serviceType
		// =1
		// Case 2: VoucherType = 'NM'& ProductGroup <> 'ThanNguyenKhai' - serviceType =
		// 2
		// Case 3: (VoucherType = 'NCB' |VoucherType = 'NNB' )& ProductGroup <>
		// 'ThanNguyenKhai' - serviceType = 3
		if ("NM".equalsIgnoreCase(voucherType)) {
			if ("ThanNguyenKhai".equalsIgnoreCase(productGroup) || "SanPhamNgoaiThan".equalsIgnoreCase(productGroup)) {
				return NhapXuatType.NHAP_THAN_NK_CTY_KHAC;
			} else {
				return NhapXuatType.NHAP_THAN_SACH_CTY_KHAC;
			}
		}

		if (("NCB".equalsIgnoreCase(voucherType) || "NNB".equalsIgnoreCase(voucherType))
				&& !"ThanNguyenKhai".equalsIgnoreCase(productGroup)) {
			return NhapXuatType.NHAP_THAN_SACH_NOI_BO;
		}

//		Case 1: Xuất than sạch - Khác: (VoucherType = 'XB' ) & ProductGroup <> 'ThanNguyenKhai' - serviceType = 4
//		Case 2: Xuất than NK - Nội bộ: VoucherType = 'XCB' & ProductGroup = 'ThanNguyenKhai' - serviceType = 5
//		Case 3: Xuất than sạch - Nội bộ: (VoucherType = 'XCB' |VoucherType = 'XNB') & ProductGroup <> 'ThanNguyenKhai' - serviceType = 6
		if (("XB".equalsIgnoreCase(voucherType)) && !"ThanNguyenKhai".equalsIgnoreCase(productGroup)) {
			return NhapXuatType.XUAT_THAN_SACH_CTY_KHAC;
		}

		if ("XCB".equalsIgnoreCase(voucherType) && ("ThanNguyenKhai".equalsIgnoreCase(productGroup) || "SanPhamNgoaiThan".equalsIgnoreCase(productGroup))) {
			return NhapXuatType.XUAT_THAN_NK_NOI_BO;
		}

		if (("XCB".equalsIgnoreCase(voucherType) || "XNB".equalsIgnoreCase(voucherType))
				&& !"ThanNguyenKhai".equalsIgnoreCase(productGroup)) {
			return NhapXuatType.XUAT_THAN_SACH_NOI_BO;
		}

		return null;
	}

	@Override
	@Transactional
	public int updateStatus(String ids, Integer status) {
		logger.info("Reset Nhap Xuat - Start !\"");
		String idArr[] = Convert.toStrArray(ids);
		int result = 0;
//		int status = 3;
//		switch (status) {
//		case 0:
//			status = ProcessStatus.NOTSEND.value();
//			break;
//		case 4:
//			status = ProcessStatus.FAIL.value();
//			break;
//		default:
//			status = ProcessStatus.SUCCESS.value();
//		}
		for (String id : idArr) {
			Long idL = Convert.toLong(id);
			NhapXuat nhapXuatExist = nhapXuatMapper.selectNhapXuatById(idL);
			if (nhapXuatExist != null) {
				NhapXuat nhapXuatUpdate = new NhapXuat();
				nhapXuatUpdate.setId(idL);
				nhapXuatUpdate.setStatus(status);
				nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);
				result++;
			}
		}
		return result;
	}

	@Override
	@Transactional
	public void retry(String ids) throws Exception {
		try {
			// Get a robot available
			List<Robot> robots = robotMapper.selectRobotByService(RobotServiceType.SYNC_NHAP_XUAT.value(),
					RobotStatusType.AVAILABLE.value());
			// Case if have not any robot available
			if (CollectionUtils.isEmpty(robots)) {
				throw new Exception("Không tìm thấy robot khả dụng để làm lệnh!");
			}

			Long[] idArr = Convert.toLongArray(ids);
			Date now = new Date();
			Map<RobotSyncGslctReq, Robot> robotRequestMap = new HashMap<RobotSyncGslctReq, Robot>();
			for (Long nhapXuatId : idArr) {
				NhapXuat nhapXuat = nhapXuatMapper.selectNhapXuatById(nhapXuatId);

				if (nhapXuat == null) {
					logger.info("NhapXuat not exist : {}" + nhapXuatId);
					throw new Exception("Data không tồn tại, vui lòng thử lại sau!");
				}

				WorkProcess workProcess = new WorkProcess();
				workProcess.setServiceId(RobotServiceType.SYNC_NHAP_XUAT.value());
				workProcess.setSyncId(nhapXuat.getId());
				workProcess.setSyncKey(nhapXuat.getVoucherNoGslct());
				workProcess.setPriority(RandomUtils.nextInt(0, 9));
				workProcess.setStatus(ProcessStatus.SENT.value());
				workProcess.setStartDate(now);
				workProcessMapper.insertWorkProcess(workProcess);

				NhapXuat nhapXuatUpdate = new NhapXuat();
				nhapXuatUpdate.setId(nhapXuatId);
				nhapXuatUpdate.setStatus(ProcessStatus.SENT.value());
				nhapXuatUpdate.setProcessId(workProcess.getId());
				nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);

				RobotSyncGslctReq robotSyncGslctReq = processReq(workProcess.getId(), nhapXuat);
				robotRequestMap.put(robotSyncGslctReq, robots.get(0));
			}

			if (robotRequestMap.size() > 0) {
				for (Map.Entry<RobotSyncGslctReq, Robot> entry : robotRequestMap.entrySet()) {
					requestRobot(entry.getValue(), entry.getKey());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(">>>>>> Error: " + e.getMessage());
			throw e;
		}
	}
	
	@Override
	@Transactional
	public void retry(Map<Long, Robot> requestMap) throws Exception {
		try {
			Date now = new Date();
			Map<RobotSyncGslctReq, Robot> robotRequestMap = new HashMap<RobotSyncGslctReq, Robot>();
			for (Map.Entry<Long, Robot> entry : requestMap.entrySet()) {
				NhapXuat nhapXuat = nhapXuatMapper.selectNhapXuatById(entry.getKey());

				if (nhapXuat == null) {
					logger.info("NhapXuat not exist : {}" + entry.getKey());
					throw new Exception("Data không tồn tại, vui lòng thử lại sau!");
				}

				WorkProcess workProcess = new WorkProcess();
				workProcess.setServiceId(RobotServiceType.SYNC_NHAP_XUAT.value());
				workProcess.setSyncId(nhapXuat.getId());
				workProcess.setSyncKey(nhapXuat.getVoucherNoGslct());
				workProcess.setPriority(RandomUtils.nextInt(0, 9));
				workProcess.setStatus(ProcessStatus.SENT.value());
				workProcess.setStartDate(now);
				workProcessMapper.insertWorkProcess(workProcess);

				NhapXuat nhapXuatUpdate = new NhapXuat();
				nhapXuatUpdate.setId(entry.getKey());
				nhapXuatUpdate.setStatus(ProcessStatus.SENT.value());
				nhapXuatUpdate.setProcessId(workProcess.getId());
				nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);

				RobotSyncGslctReq robotSyncGslctReq = processReq(workProcess.getId(), nhapXuat);
				robotRequestMap.put(robotSyncGslctReq, entry.getValue());
			}

			if (robotRequestMap.size() > 0) {
				for (Map.Entry<RobotSyncGslctReq, Robot> entry : robotRequestMap.entrySet()) {
					requestRobot(entry.getValue(), entry.getKey());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error(">>>>>> Error: " + e.getMessage());
			throw e;
		}

	}

	private RobotSyncGslctReq processReq(Long processId, NhapXuat nhapXuat) {
		if (nhapXuat == null) {
			return null;
		}

		RobotSyncGslctReq robotSyncGslctReq = new RobotSyncGslctReq();
		robotSyncGslctReq.setConfig(getProcessConfig());
		robotSyncGslctReq.setProcessId(processId);
		robotSyncGslctReq.setSyncId(nhapXuat.getVoucherType());
		robotSyncGslctReq.setServiceType(nhapXuat.getType());
		robotSyncGslctReq.setServiceName(getServiceName(nhapXuat.getType()));
		robotSyncGslctReq.setIsCreate((ProcessMode.INSERT.value() == nhapXuat.getMode()) ? true : false);
		robotSyncGslctReq.setVoucherNo(nhapXuat.getVoucherNoGslct());
		robotSyncGslctReq.setVoucherDate(DateUtils.parseDateToStr("dd/MM/yyyy", nhapXuat.getVoucherDate()));
		robotSyncGslctReq.setProviderName(getProviderName(nhapXuat.getProviderId()));
		robotSyncGslctReq.setStockName(getStockName(nhapXuat.getStockId()));
		robotSyncGslctReq.setDescription(nhapXuat.getDescription());
		robotSyncGslctReq.setIsChangeVoucherNo(false);
		robotSyncGslctReq.setProcessNumber(0);
		robotSyncGslctReq.setExportNumber(0);
		robotSyncGslctReq.setImportNumber(0);
		robotSyncGslctReq.setNhapXuatId(nhapXuat.getId());

		// Kiem tra thay doi so chung tu
		NhapXuat nhapXuatSelect = new NhapXuat();
		nhapXuatSelect.setVoucherId(nhapXuat.getVoucherId());
		nhapXuatSelect.setStatus(ProcessStatus.SUCCESS.value());
		List<NhapXuat> nhapXuatExistList = nhapXuatMapper.selectNhapXuatList(nhapXuatSelect);
		if (CollectionUtils.isNotEmpty(nhapXuatExistList)) {
			NhapXuat nhapXuatExist = nhapXuatExistList.get(0);
			if (StringUtils.isNotBlank(nhapXuatExist.getVoucherNoGslct())
					&& !nhapXuatExist.getVoucherNoGslct().equals(nhapXuat.getVoucherNoGslct())) {
				robotSyncGslctReq.setIsChangeVoucherNo(true);
				robotSyncGslctReq.setOldVoucherNo(nhapXuatExist.getVoucherNoGslct());
			}
		}

		// Chi tiet nhap xuat
		if (SyncType.NHAP.value() == nhapXuat.getSyncType() || SyncType.XUAT.value() == nhapXuat.getSyncType()) {
			NhapXuatDetail nhapXuatDetailSelect = new NhapXuatDetail();
			nhapXuatDetailSelect.setNhapXuatId(nhapXuat.getId());
			List<NhapXuatDetail> nhapXuatDetailList = nhapXuatDetailMapper
					.selectNhapXuatDetailList(nhapXuatDetailSelect);

			List<RobotSyncGslctDetailReq> robotSyncGslctDetailReqList = new ArrayList<RobotSyncGslctDetailReq>();
			if (CollectionUtils.isNotEmpty(nhapXuatDetailList)) {
				for (NhapXuatDetail nhapXuatDetail : nhapXuatDetailList) {
					RobotSyncGslctDetailReq robotSyncGslctDetailReq = new RobotSyncGslctDetailReq();
					robotSyncGslctDetailReq.setProductName(getProductName(nhapXuatDetail.getProductName()));
					robotSyncGslctDetailReq.setRealQuantity(nhapXuatDetail.getRealQuantity() == null ? "0"
							: nhapXuatDetail.getRealQuantity().toString());
					robotSyncGslctDetailReq.setAkRate(
							nhapXuatDetail.getAkRate() == null ? "0" : nhapXuatDetail.getAkRate().toString());
					robotSyncGslctDetailReq.setWtpRate(
							nhapXuatDetail.getWtpRate() == null ? "0" : nhapXuatDetail.getWtpRate().toString());
					robotSyncGslctDetailReq.setConvertQuantity(nhapXuatDetail.getConvertQuantity() == null ? "0"
							: nhapXuatDetail.getConvertQuantity().toString());
					robotSyncGslctDetailReqList.add(robotSyncGslctDetailReq);
				}
				robotSyncGslctReq.setProcessOrder(robotSyncGslctDetailReqList);
				robotSyncGslctReq.setProcessNumber(robotSyncGslctDetailReqList.size());
			}
		}

		if (SyncType.PHA_TRON.value() == nhapXuat.getSyncType()) {
			// Chi tiet nhap
			NhapXuatDetail nhapXuatDetailSelect = new NhapXuatDetail();
			nhapXuatDetailSelect.setNhapXuatId(nhapXuat.getId());
			nhapXuatDetailSelect.setVoucherId(nhapXuat.getImportId());
			List<NhapXuatDetail> nhapDetailList = nhapXuatDetailMapper.selectNhapXuatDetailList(nhapXuatDetailSelect);

			List<RobotSyncGslctDetailReq> importDetailList = new ArrayList<RobotSyncGslctDetailReq>();
			if (CollectionUtils.isNotEmpty(nhapDetailList)) {
				for (NhapXuatDetail nhapDetail : nhapDetailList) {
					RobotSyncGslctDetailReq robotSyncGslctDetailReq = new RobotSyncGslctDetailReq();
					robotSyncGslctDetailReq.setProductName(getProductName(nhapDetail.getProductName()));
					robotSyncGslctDetailReq.setRealQuantity(
							nhapDetail.getRealQuantity() == null ? "0" : nhapDetail.getRealQuantity().toString());
					robotSyncGslctDetailReq
							.setAkRate(nhapDetail.getAkRate() == null ? "0" : nhapDetail.getAkRate().toString());
					robotSyncGslctDetailReq
							.setWtpRate(nhapDetail.getWtpRate() == null ? "0" : nhapDetail.getWtpRate().toString());
					robotSyncGslctDetailReq.setConvertQuantity(
							nhapDetail.getConvertQuantity() == null ? "0" : nhapDetail.getConvertQuantity().toString());
					robotSyncGslctDetailReq.setStockName(getStockName(nhapDetail.getStockId()));
					importDetailList.add(robotSyncGslctDetailReq);
				}
				robotSyncGslctReq.setImportOrder(importDetailList);
				robotSyncGslctReq.setImportNumber(importDetailList.size());
			}

			// Chi tiet xuat
			nhapXuatDetailSelect = new NhapXuatDetail();
			nhapXuatDetailSelect.setNhapXuatId(nhapXuat.getId());
			nhapXuatDetailSelect.setVoucherId(nhapXuat.getExportId());
			List<NhapXuatDetail> xuatDetailList = nhapXuatDetailMapper.selectNhapXuatDetailList(nhapXuatDetailSelect);

			List<RobotSyncGslctDetailReq> exportDetailList = new ArrayList<RobotSyncGslctDetailReq>();
			if (CollectionUtils.isNotEmpty(xuatDetailList)) {
				for (NhapXuatDetail xuatDetail : xuatDetailList) {
					RobotSyncGslctDetailReq robotSyncGslctDetailReq = new RobotSyncGslctDetailReq();
					robotSyncGslctDetailReq.setProductName(getProductName(xuatDetail.getProductName()));
					robotSyncGslctDetailReq.setRealQuantity(
							xuatDetail.getRealQuantity() == null ? "0" : xuatDetail.getRealQuantity().toString());
					robotSyncGslctDetailReq
							.setAkRate(xuatDetail.getAkRate() == null ? "0" : xuatDetail.getAkRate().toString());
					robotSyncGslctDetailReq
							.setWtpRate(xuatDetail.getWtpRate() == null ? "0" : xuatDetail.getWtpRate().toString());
					robotSyncGslctDetailReq.setConvertQuantity(
							xuatDetail.getConvertQuantity() == null ? "0" : xuatDetail.getConvertQuantity().toString());
					robotSyncGslctDetailReq.setStockName(getStockName(xuatDetail.getStockId()));
					exportDetailList.add(robotSyncGslctDetailReq);
				}
				robotSyncGslctReq.setExportOrder(exportDetailList);
				robotSyncGslctReq.setExportNumber(exportDetailList.size());
			}
		}

		return robotSyncGslctReq;
	}

	private ProcessGslctConfig getProcessConfig() {
		ProcessGslctConfig processMisaConfig = new ProcessGslctConfig();
		processMisaConfig.setUsername(configService.selectConfigByKey("business.web.gslct.username"));
		processMisaConfig.setPassword(configService.selectConfigByKey("business.web.gslct.password"));
		processMisaConfig.setUrl(configService.selectConfigByKey("business.web.gslct.url"));
		return processMisaConfig;
	}

	private String getServiceName(int type) {
		NhapXuatType nhapXuatType = NhapXuatType.fromValue(type);
		if (nhapXuatType == null) {
			return null;
		}
		return nhapXuatType.title();

	}

	private void requestRobot(Robot robot, RobotSyncGslctReq process) {
//		// Get a robot available
//		List<Robot> robots = robotMapper.selectRobotByService(RobotServiceType.SYNC_NHAP_XUAT.value(),
//				RobotStatusType.AVAILABLE.value());
//		// Case if have not any robot available
//		if (CollectionUtils.isEmpty(robots)) {
//			String errMsg = "Không tìm thấy robot khả dụng để làm lệnh!";
//			WorkProcess processUpdate = new WorkProcess();
//			processUpdate.setId(process.getProcessId());
//			processUpdate.setStatus(ProcessStatus.FAIL.value());
//			processUpdate.setEndDate(new Date());
//			processUpdate.setError(errMsg);
//			workProcessMapper.updateWorkProcess(processUpdate);
//
//			NhapXuat nhapXuatUpdate = new NhapXuat();
//			nhapXuatUpdate.setId(process.getNhapXuatId());
//			nhapXuatUpdate.setStatus(ProcessStatus.FAIL.value());
//			nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);
//			return;
//		}
//		Robot robot = robots.get(0);

		// Push Mqtt
		String message = new Gson().toJson(process);

		WorkProcess processUpdate = new WorkProcess();
		processUpdate.setId(process.getProcessId());
		processUpdate.setDataRequest(message);
		processUpdate.setRobotUuid(robot.getUuid());
		workProcessMapper.updateWorkProcess(processUpdate);

		String topic = MqttService.TOPIC_ROBOT_BASE + "/" + robot.getUuid() + "/process/request";
		logger.info("Send Mqtt topic {} with message {}", topic, message);
		try {
			mqttService.publish(topic, new MqttMessage(message.getBytes("UTF-8")));
		} catch (Exception e) {
			processUpdate = new WorkProcess();
			processUpdate.setId(process.getProcessId());
			processUpdate.setStatus(ProcessStatus.FAIL.value());
			processUpdate.setEndDate(new Date());
			processUpdate.setError(e.getMessage());
			workProcessMapper.updateWorkProcess(processUpdate);

			NhapXuat nhapXuatUpdate = new NhapXuat();
			nhapXuatUpdate.setId(process.getNhapXuatId());
			nhapXuatUpdate.setStatus(ProcessStatus.FAIL.value());
			nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);
			logger.error("Error when try sending mqtt message: " + e);
		}
	}

	private String getProviderName(String providerId) {

		if (StringUtils.isBlank(providerId)) {
			return providerId;
		}

		SysDictData sysDictDataSelect = new SysDictData();
		sysDictDataSelect.setDictType("business_provider_type");
		sysDictDataSelect.setDictLabel(providerId);
		List<SysDictData> sysDictDataExistList = dictDataService.selectDictDataList(sysDictDataSelect);
		if (CollectionUtils.isNotEmpty(sysDictDataExistList)) {
			return sysDictDataExistList.get(0).getDictValue();
		}
		return providerId;
	}

	private String getStockId(String stockId) {

		if (StringUtils.isBlank(stockId)) {
			return stockId;
		}

		SysDictData sysDictDataSelect = new SysDictData();
		sysDictDataSelect.setDictType("business_stock_id_type");
		sysDictDataSelect.setDictLabel(stockId);
		List<SysDictData> sysDictDataExistList = dictDataService.selectDictDataList(sysDictDataSelect);
		if (CollectionUtils.isNotEmpty(sysDictDataExistList)) {
			return sysDictDataExistList.get(0).getDictValue();
		}
		return stockId;
	}

	private String getStockName(String stockId) {

		if (StringUtils.isBlank(stockId)) {
			return stockId;
		}

		SysDictData sysDictDataSelect = new SysDictData();
		sysDictDataSelect.setDictType("business_stock_name_type");
		sysDictDataSelect.setDictLabel(stockId);
		List<SysDictData> sysDictDataExistList = dictDataService.selectDictDataList(sysDictDataSelect);
		if (CollectionUtils.isNotEmpty(sysDictDataExistList)) {
			return sysDictDataExistList.get(0).getDictValue();
		}
		return stockId;
	}

	private String getProductName(String productName) {
		if (StringUtils.isBlank(productName)) {
			return productName;
		}

		List<SysDictData> sysDictDataExistList = dictDataService.selectDictDataByType("business_product_type");
		if (CollectionUtils.isNotEmpty(sysDictDataExistList)) {

			for (SysDictData sysDictData : sysDictDataExistList) {
				try {
					if (StringUtils.isNotEmpty(sysDictData.getDictLabel())
							&& (productName.equalsIgnoreCase(sysDictData.getDictLabel())
									|| productName.matches(sysDictData.getDictLabel()))) {
						return sysDictData.getDictValue();
					}
				} catch (Exception ex) {
				}
			}
		}
		return productName;
	}

	@Override
	public int countByStatuses(Integer[] statuses, Date fromDate, Date toDate, Integer syncType) {
		return nhapXuatMapper.countByStatuses(statuses, fromDate, toDate, syncType);
	}
}
