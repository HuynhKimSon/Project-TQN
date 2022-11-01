package vn.com.irtech.irbot.web.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.irtech.core.common.utils.DateUtils;
import vn.com.irtech.irbot.business.dto.request.SyncDataNhapXuatReq;
import vn.com.irtech.irbot.business.service.INhapXuatService;

@Component("syncNhapXuatTask")
public class SyncNhapXuatTask {
	private static final Logger logger = LoggerFactory.getLogger(SyncNhapXuatTask.class);
	
	@Autowired
	private INhapXuatService nhapXuatService;

	public void executeTask(String mode) {
		logger.info(">>>>>>>>>>>>>> SYNC NHAP XUAT TASK - START!");

		try {
			Date endDate = new Date();
			Date startDate = new Date();
			
			if (!"1".equalsIgnoreCase(mode)) {
				startDate = DateUtils.addDays(startDate, -1);
			}
			
			SyncDataNhapXuatReq request = new SyncDataNhapXuatReq();
			request.setFromDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, startDate));
			request.setToDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, endDate));
			
			request.setIsAuto(true);
			nhapXuatService.sync(request);

		} catch (Exception e) {

			logger.error(">>>>>> Error: " + e.getMessage());
		}

		logger.info(">>>>>>>>>>>>>> SYNC NHAP XUAT TASK - FINISH!");
	}
}
