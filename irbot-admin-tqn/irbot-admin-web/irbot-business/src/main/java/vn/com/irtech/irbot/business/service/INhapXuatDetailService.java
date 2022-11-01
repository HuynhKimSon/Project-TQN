package vn.com.irtech.irbot.business.service;

import java.util.List;

import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.NhapXuatDetail;
import vn.com.irtech.irbot.business.type.SyncType;

public interface INhapXuatDetailService {
	/**
     * Query nhapXuat_detail
     *
     * @param id ID nhapXuat_detail
     * @return nhapXuat_detail
     */
    public NhapXuatDetail selectNhapXuatDetailById(Long id);

    /**
     * Query list nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * Collection @return nhapXuat_detail
     */
    public List<NhapXuatDetail> selectNhapXuatDetailList(NhapXuatDetail nhapXuatDetail);

    /**
     * Added nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * @return result
     */
    public int insertNhapXuatDetail(NhapXuatDetail nhapXuatDetail);

    /**
     * Update nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * @return result
     */
    public int updateNhapXuatDetail(NhapXuatDetail nhapXuatDetail);

    /**
     * Xóa hàng loạt nhapXuat_detail
     *
     * @param id ID của dữ liệu sẽ bị xóa
     * @return result
     */
    public int deleteNhapXuatDetailByIds(String ids);

    /**
     * Delete information nhapXuat_detail
     *
     * @param id ID nhapXuat_detail
     * @return result
     */
    public int deleteNhapXuatDetailById(Long id);
    
    public List<NhapXuatDetail> selectNhapXuatDetailList(long nhapXuatId, long voucherId);
}
