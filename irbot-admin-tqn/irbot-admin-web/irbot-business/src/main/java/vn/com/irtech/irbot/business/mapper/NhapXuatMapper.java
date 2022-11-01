package vn.com.irtech.irbot.business.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import vn.com.irtech.irbot.business.domain.NhapXuat;

public interface NhapXuatMapper {
	/**
	 * Query nhapxuat
	 *
	 * @param id ID nhapxuat
	 * @return nhapxuat
	 */
	public NhapXuat selectNhapXuatById(Long id);

	/**
	 * Query list nhapxuat
	 *
	 * @param nhapxuat nhapxuat Collection @return nhapxuat
	 */
	public List<NhapXuat> selectNhapXuatList(NhapXuat nhapxuat);

	/**
	 * Added nhapxuat
	 *
	 * @param nhapxuat nhapxuat
	 * @return result
	 */
	public int insertNhapXuat(NhapXuat nhapxuat);

	/**
	 * Update nhapxuat
	 *
	 * @param nhapxuat nhapxuat
	 * @return result
	 */
	public int updateNhapXuat(NhapXuat nhapxuat);

	/**
	 * Delete nhapxuat
	 *
	 * @param id ID nhapxuat
	 * @return result
	 */
	public int deleteNhapXuatById(Long id);

	/**
	 * Bulk delete nhapxuat
	 *
	 * @param id The ID of the data will be deleted
	 * @return result
	 */
	public int deleteNhapXuatByIds(String[] ids);

	/**
	 * Thong ke lenh nhap xuat
	 * @param statuses
	 * @param fromDate
	 * @param toDate
	 * @param syncType
	 * @return
	 */
	public int countByStatuses(@Param("statuses") Integer[] statuses, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate, @Param("syncType") Integer syncType);
}
