package vn.com.irtech.irbot.business.mapper;

import java.util.List;

import vn.com.irtech.irbot.business.domain.NhapXuatDetail;

public interface NhapXuatDetailMapper {
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
     * Delete nhapXuat_detail
     *
     * @param id ID nhapXuat_detail
     * @return result
     */
    public int deleteNhapXuatDetailById(Long id);

    /**
     * Bulk delete nhapXuat_detail
     *
     * @param id The ID of the data will be deleted
     * @return result
     */
    public int deleteNhapXuatDetailByIds(String[] ids);
    
    public int deleteNhapXuatDetailByNhapXuatIds(String[] ids);
}
