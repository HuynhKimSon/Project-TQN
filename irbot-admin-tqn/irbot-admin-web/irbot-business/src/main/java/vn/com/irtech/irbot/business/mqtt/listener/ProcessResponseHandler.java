package vn.com.irtech.irbot.business.mqtt.listener;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.WorkProcess;
import vn.com.irtech.irbot.business.dto.response.RobotProcessRes;
import vn.com.irtech.irbot.business.mapper.NhapXuatMapper;
import vn.com.irtech.irbot.business.mapper.WorkProcessMapper;
import vn.com.irtech.irbot.business.type.ProcessStatus;

@Component
public class ProcessResponseHandler implements IMqttMessageListener {

	private static final Logger logger = LoggerFactory.getLogger(ProcessResponseHandler.class);

	@Autowired
	@Qualifier("threadPoolTaskExecutor")
	private TaskExecutor executor;

	@Autowired
	private WorkProcessMapper workProcessMapper;

	@Autowired
	private NhapXuatMapper nhapXuatMapper;

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					processMessage(topic, message);
				} catch (Exception e) {
					logger.error("Error while process mq message", e);
					e.printStackTrace();
				}
			}
		});
	}

	@Transactional
	private void processMessage(String topic, MqttMessage message) throws Exception {
		String messageContent = new String(message.getPayload(), StandardCharsets.UTF_8);
		logger.info(">>>> Receive message topic: {}, content {}", topic, messageContent);
		RobotProcessRes response = null;
		try {
			response = new Gson().fromJson(messageContent, RobotProcessRes.class);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

//		if (response.getSyncId() == null) {
//			return;
//		}

		Long processId = response.getProcessId();
		String result = response.getResult();
		String errMsg = response.getErrorMsg();
		String robotUuid = topic.split("/")[3];

		updateProcessGslctStatus(robotUuid, messageContent, processId, result, errMsg);
		return;
	}

	private void updateProcessGslctStatus(String robotUuid, String messageContent, Long processId, String result,
			String errorMsg) {
		// Get process by Id
		WorkProcess process = workProcessMapper.selectWorkProcessById(processId);
		if (process == null) {
			logger.warn("Work Process not exist: {}", processId);
			return;
		}
		String errMsg = null;
		ProcessStatus processStatus = null;
		switch (result.toUpperCase()) {
		case "SUCCESS":
			processStatus = ProcessStatus.SUCCESS;
			break;
		case "PROCESSING":
			processStatus = ProcessStatus.PROCESSING;
			break;
		case "FAIL":
			processStatus = ProcessStatus.FAIL;
			errMsg = errorMsg;
			break;
		default:
			return;
		}

		WorkProcess processUpdate = new WorkProcess();
		processUpdate.setId(processId);
		processUpdate.setDataResponse(messageContent);
		processUpdate.setRobotUuid(robotUuid);
		if (processStatus != ProcessStatus.PROCESSING) {
			processUpdate.setEndDate(new Date());
		}
		processUpdate.setStatus(processStatus.value());
		processUpdate.setError(errMsg);
		workProcessMapper.updateWorkProcess(processUpdate);

		// Update nhap xuat status
		NhapXuat nhapXuatUpdate = new NhapXuat();
		nhapXuatUpdate.setId(process.getSyncId());
		nhapXuatUpdate.setStatus(processStatus.value());
		nhapXuatMapper.updateNhapXuat(nhapXuatUpdate);
	}

}