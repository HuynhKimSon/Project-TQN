package vn.com.irtech.irbot.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.com.irtech.core.common.text.Convert;
import vn.com.irtech.irbot.business.domain.NhapXuatDetail;
import vn.com.irtech.irbot.business.mapper.NhapXuatDetailMapper;
import vn.com.irtech.irbot.business.service.INhapXuatDetailService;

@Service
public class NhapXuatDetailServiceImpl implements INhapXuatDetailService 
{
    @Autowired
    private NhapXuatDetailMapper nhapXuatDetailMapper;

    /**
     * Query nhapXuat_detail
     *
     * @param id ID nhapXuat_detail
     * @return nhapXuat_detail
     */
    @Override
    public NhapXuatDetail selectNhapXuatDetailById(Long id)
    {
        return nhapXuatDetailMapper.selectNhapXuatDetailById(id);
    }


    /**
     * Query list nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * @return nhapXuat_detail
     */
    @Override
    public List<NhapXuatDetail> selectNhapXuatDetailList(NhapXuatDetail nhapXuatDetail)
    {
        return nhapXuatDetailMapper.selectNhapXuatDetailList(nhapXuatDetail);
    }


    /**
     * Added nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * @return result
     */
    @Override
    public int insertNhapXuatDetail(NhapXuatDetail nhapXuatDetail)
    {
        return nhapXuatDetailMapper.insertNhapXuatDetail(nhapXuatDetail);
    }

    /**
     * Update nhapXuat_detail
     *
     * @param nhapXuatDetail nhapXuat_detail
     * @return result
     */
    @Override
    public int updateNhapXuatDetail(NhapXuatDetail nhapXuatDetail)
    {
        return nhapXuatDetailMapper.updateNhapXuatDetail(nhapXuatDetail);
    }

    /**
     * Delete object nhapXuat_detail
     *
     * @param id ID of the data to be deleted
     * @return result
     */
    @Override
    public int deleteNhapXuatDetailByIds(String ids)
    {
        return nhapXuatDetailMapper.deleteNhapXuatDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * Delete information nhapXuat_detail
     *
     * @param id ID nhapXuat_detail
     * @return result
     */
    @Override
    public int deleteNhapXuatDetailById(Long id)
    {
        return nhapXuatDetailMapper.deleteNhapXuatDetailById(id);
    }


	@Override
	public List<NhapXuatDetail> selectNhapXuatDetailList(long nhapXuatId, long voucherId) {
		NhapXuatDetail nhapXuatDetailSelect = new NhapXuatDetail();
		nhapXuatDetailSelect.setNhapXuatId(nhapXuatId);
		nhapXuatDetailSelect.setVoucherId(voucherId);
		return nhapXuatDetailMapper.selectNhapXuatDetailList(nhapXuatDetailSelect);
	}
}
