package vn.com.irtech.irbot.business.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import vn.com.irtech.irbot.business.client.ApiClient;
import vn.com.irtech.irbot.business.dto.DismantionMaster;
import vn.com.irtech.irbot.business.dto.VoucherMaster;
import vn.com.irtech.irbot.business.dto.request.ApiGetNhapXuatDataReq;
import vn.com.irtech.irbot.business.dto.response.ApiNhapXuatRes;
import vn.com.irtech.irbot.business.dto.response.ApiPhaTronRes;
import vn.com.irtech.irbot.business.service.IApiGatewayService;

@Service
public class ApiGatewayServiceImpl implements IApiGatewayService {

	private static final Logger logger = LoggerFactory.getLogger(ApiGatewayServiceImpl.class);

	@Value("${api.apiUrl}")
	private String apiUrl;

	@Override
	public List<VoucherMaster> getImportData(ApiGetNhapXuatDataReq request) throws Exception {
		try {
			String url = apiUrl + "/api/import/getData";
			ApiClient apiClient = new ApiClient();
			
			HttpEntity<ApiGetNhapXuatDataReq> httpEntity = new HttpEntity<ApiGetNhapXuatDataReq>(request);
			ResponseEntity<ApiNhapXuatRes> responseEntity = apiClient.exchange(url, HttpMethod.POST, httpEntity,
					new ParameterizedTypeReference<ApiNhapXuatRes>() {
					});

			if (responseEntity == null) {
				return null;
			}

			ApiNhapXuatRes responseData = responseEntity.getBody();

			if (responseData.getCode() == 500) {
				throw new Exception(responseData.getMsg());
			}

			return responseData.getData();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}

	@Override
	public List<VoucherMaster> getExportData(ApiGetNhapXuatDataReq request) throws Exception {
		try {
			String url = apiUrl + "/api/export/getData";
			ApiClient apiClient = new ApiClient();

			HttpEntity<ApiGetNhapXuatDataReq> httpEntity = new HttpEntity<ApiGetNhapXuatDataReq>(request);
			ResponseEntity<ApiNhapXuatRes> responseEntity = apiClient.exchange(url, HttpMethod.POST, httpEntity,
					new ParameterizedTypeReference<ApiNhapXuatRes>() {
					});

			if (responseEntity == null) {
				return null;
			}

			ApiNhapXuatRes responseData = responseEntity.getBody();

			if (responseData.getCode() == 500) {
				throw new Exception(responseData.getMsg());
			}

			return responseData.getData();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}

	@Override
	public List<DismantionMaster> getDismantionData(ApiGetNhapXuatDataReq request) throws Exception {
		try {
			String url = apiUrl + "/api/dismantion/getData";
			ApiClient apiClient = new ApiClient();

			HttpEntity<ApiGetNhapXuatDataReq> httpEntity = new HttpEntity<ApiGetNhapXuatDataReq>(request);
			ResponseEntity<ApiPhaTronRes> responseEntity = apiClient.exchange(url, HttpMethod.POST, httpEntity,
					new ParameterizedTypeReference<ApiPhaTronRes>() {
					});

			if (responseEntity == null) {
				return null;
			}

			ApiPhaTronRes responseData = responseEntity.getBody();

			if (responseData.getCode() == 500) {
				throw new Exception(responseData.getMsg());
			}

			return responseData.getData();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw ex;
		}
	}

}
