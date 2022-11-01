package vn.com.irtech.irbot.business.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.Robot;
import vn.com.irtech.irbot.business.dto.request.SyncDataNhapXuatReq;

/**
 * Service interface nhapXuat
 *
 * @author irtech
 * @date 2022-04-27
 */
public interface INhapXuatService {

	/**
	 * Query nhapXuat
	 *
	 * @param id ID nhapXuat
	 * @return nhapXuat
	 */
	public NhapXuat selectNhapXuatById(Long id);

	/**
	 * Query list nhapXuat
	 *
	 * @param nhapXuat nhapXuat Collection @return nhapXuat
	 */
	public List<NhapXuat> selectNhapXuatList(NhapXuat nhapXuat);

	/**
	 * Added nhapXuat
	 *
	 * @param nhapXuat nhapXuat
	 * @return result
	 */
	public int insertNhapXuat(NhapXuat nhapXuat);

	/**
	 * Update nhapXuat
	 *
	 * @param nhapXuat nhapXuat
	 * @return result
	 */
	public int updateNhapXuat(NhapXuat nhapXuat);

	/**
	 * Xóa hàng loạt nhapXuat
	 *
	 * @param id ID của dữ liệu sẽ bị xóa
	 * @return result
	 */
	public int deleteNhapXuatByIds(String ids);

	/**
	 * Delete information nhapXuat
	 *
	 * @param id ID nhapXuat
	 * @return result
	 */
	public int deleteNhapXuatById(Long id);

	public int sync(SyncDataNhapXuatReq request) throws Exception;

	public void retry(String ids) throws Exception;
	
	public void retry(Map<Long, Robot> requestMap) throws Exception;

	public int countByStatuses(Integer[] statuses, Date fromDate, Date toDate, Integer syncType);

	int updateStatus(String ids, Integer status);
}
