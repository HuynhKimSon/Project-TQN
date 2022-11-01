package vn.com.irtech.irbot.web.task;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.com.irtech.core.common.utils.DateUtils;
import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.Robot;
import vn.com.irtech.irbot.business.mapper.RobotMapper;
import vn.com.irtech.irbot.business.service.INhapXuatService;
import vn.com.irtech.irbot.business.type.ProcessStatus;
import vn.com.irtech.irbot.business.type.RobotServiceType;
import vn.com.irtech.irbot.business.type.RobotStatusType;

@Component("sendRobotTask")
public class SendRobotTask {

	private static final Logger logger = LoggerFactory.getLogger(SendRobotTask.class);

	@Autowired
	private RobotMapper robotMapper;

	@Autowired
	private INhapXuatService nhapXuatService;

	public void executeTask() {
		logger.info(">>>>>>>>>>>>>> SEND ROBOT TASK - START!");

		try {
			Date now = new Date();

			NhapXuat nhapXuatSelect = new NhapXuat();
			nhapXuatSelect.setAuto("1");
			nhapXuatSelect.getParams().put("beginTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, now));
			nhapXuatSelect.getParams().put("endTime", DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD, now));
			nhapXuatSelect.setStatus(ProcessStatus.NOTSEND.value());
			nhapXuatSelect.getParams().put("orderAsc", true);
			List<NhapXuat> nhapXuatList = nhapXuatService.selectNhapXuatList(nhapXuatSelect);

			// check if empty
			if (CollectionUtils.isEmpty(nhapXuatList)) {
				throw new Exception("Không có data!");
			}
			
			List<Robot> robots = robotMapper.selectRobotByService(RobotServiceType.SYNC_NHAP_XUAT.value(),
					RobotStatusType.AVAILABLE.value());

			// Case if have not any robot available
			if (CollectionUtils.isEmpty(robots)) {
				logger.info("Không tìm thấy robot khả dụng để làm lệnh!");
				logger.info(">>>>>>>>>>>>>> SEND ROBOT TASK - FINISH!");
				throw new Exception("Không tìm thấy robot khả dụng để làm lệnh!");
			}
			
			Map<Long, Robot> requestMap = new HashMap<Long, Robot>();
			for (NhapXuat nhapXuat : nhapXuatList) {
				if (CollectionUtils.isEmpty(robots)) {
					break;
				}
				
				requestMap.put(nhapXuat.getId(), robots.get(0));
				
				robots.remove(0);
			}
			
			
			nhapXuatService.retry(requestMap);
			

		} catch (Exception e) {

			logger.error(">>>>>> Error: " + e.getMessage());
		}

		logger.info(">>>>>>>>>>>>>> SEND ROBOT TASK - FINISH!");
	}

}
