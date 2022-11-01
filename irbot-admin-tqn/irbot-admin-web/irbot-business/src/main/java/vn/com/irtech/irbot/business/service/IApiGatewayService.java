package vn.com.irtech.irbot.business.service;

import java.util.List;

import vn.com.irtech.irbot.business.dto.DismantionMaster;
import vn.com.irtech.irbot.business.dto.VoucherMaster;
import vn.com.irtech.irbot.business.dto.request.ApiGetNhapXuatDataReq;
import vn.com.irtech.irbot.business.dto.response.ApiNhapXuatRes;
import vn.com.irtech.irbot.business.dto.response.PhaTronRes;

public interface IApiGatewayService {
	
	public List<VoucherMaster> getImportData(ApiGetNhapXuatDataReq request) throws Exception;
	
	public List<VoucherMaster> getExportData(ApiGetNhapXuatDataReq request) throws Exception;
	
	public List<DismantionMaster> getDismantionData(ApiGetNhapXuatDataReq request) throws Exception;
	
}
