package vn.com.irtech.irbot.web.controller.business;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.com.irtech.core.common.annotation.Log;
import vn.com.irtech.core.common.controller.BaseController;
import vn.com.irtech.core.common.domain.AjaxResult;
import vn.com.irtech.core.common.enums.BusinessType;
import vn.com.irtech.core.common.page.TableDataInfo;
import vn.com.irtech.irbot.business.domain.NhapXuat;
import vn.com.irtech.irbot.business.domain.WorkProcess;
import vn.com.irtech.irbot.business.dto.request.SyncDataNhapXuatReq;
import vn.com.irtech.irbot.business.dto.request.UpdateNhapXuatStatusReq;
import vn.com.irtech.irbot.business.service.INhapXuatDetailService;
import vn.com.irtech.irbot.business.service.INhapXuatService;
import vn.com.irtech.irbot.business.service.IWorkProcessService;
import vn.com.irtech.irbot.business.type.SyncType;

@Controller
@RequestMapping("/business/process-gslct")
public class ProcessGslctController extends BaseController {

	private String prefix = "business/process-gslct";

	@Autowired
	private INhapXuatService nhapXuatService;

	@Autowired
	private IWorkProcessService workProcessService;

	@Autowired
	private INhapXuatDetailService nhapXuatDetailService;

	@RequiresPermissions("business:process-gslct:view")
	@GetMapping()
	public String processGslct() {
		return prefix + "/process-gslct";
	}

	@RequiresPermissions("business:process-gslct:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NhapXuat nhapXuat) {
		startPage();
		List<NhapXuat> list = nhapXuatService.selectNhapXuatList(nhapXuat);
		return getDataTable(list);
	}

	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		NhapXuat entity = nhapXuatService.selectNhapXuatById(id);
		mmap.put("entity", entity);
		return prefix + "/edit";
	}

	@RequiresPermissions("business:process-gslct:edit")
	@Log(title = "Robot", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(@Validated @RequestBody NhapXuat nhapXuat) {
		return toAjax(nhapXuatService.updateNhapXuat(nhapXuat));
	}

	@RequiresPermissions("business:process-gslct:remove")
	@Log(title = "Robot", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(nhapXuatService.deleteNhapXuatByIds(ids));
	}

	@GetMapping("/sync")
	public String sync() {
		return prefix + "/sync";
	}

	@PostMapping("/sync")
	@ResponseBody
	public AjaxResult sync(@Validated @RequestBody SyncDataNhapXuatReq request) {
		try {
			request.setIsAuto(false);
			int num = nhapXuatService.sync(request);
			AjaxResult ajaxResult = AjaxResult.success();
			ajaxResult.put("result", num);
			return ajaxResult;
		} catch (Exception e) {
			logger.error(">>>>>> Error: " + e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, ModelMap mmap) {
		NhapXuat nhapxuat = nhapXuatService.selectNhapXuatById(id);
		mmap.put("nhapxuat", nhapxuat);

		if (nhapxuat != null) {
			if (SyncType.NHAP.value() == nhapxuat.getSyncType()) {
				mmap.put("importDetails",
						nhapXuatDetailService.selectNhapXuatDetailList(nhapxuat.getId(), nhapxuat.getVoucherId()));
			} else if (SyncType.XUAT.value() == nhapxuat.getSyncType()) {
				mmap.put("exportDetails",
						nhapXuatDetailService.selectNhapXuatDetailList(nhapxuat.getId(), nhapxuat.getVoucherId()));
			} else if (SyncType.PHA_TRON.value() == nhapxuat.getSyncType()) {
				if (nhapxuat.getImportId() != null) {
					mmap.put("importDetails",
							nhapXuatDetailService.selectNhapXuatDetailList(nhapxuat.getId(), nhapxuat.getImportId()));
				}

				if (nhapxuat.getExportId() != null) {
					mmap.put("exportDetails",
							nhapXuatDetailService.selectNhapXuatDetailList(nhapxuat.getId(), nhapxuat.getExportId()));
				}
			}
		}

		return prefix + "/detail";
	}

	@GetMapping("/history/{id}")
	public String history(@PathVariable("id") Long id, ModelMap mmap) {
		NhapXuat nhapxuat = nhapXuatService.selectNhapXuatById(id);

		WorkProcess process = new WorkProcess();
		if (nhapxuat != null && nhapxuat.getProcessId() != null) {
			process = workProcessService.selectWorkProcessById(nhapxuat.getProcessId());
		}
		mmap.put("process", process);

		return prefix + "/history";
	}

	@GetMapping("/update-status")
	public String updateStatus() {
		return prefix + "/update-status";
	}

	@PostMapping("/update-status")
	@ResponseBody
	public AjaxResult updateStatus(@Validated @RequestBody UpdateNhapXuatStatusReq request) {
		try {
			nhapXuatService.updateStatus(request.getIds(), request.getStatus());
			AjaxResult ajaxResult = AjaxResult.success();
			return ajaxResult;
		} catch (Exception e) {
			logger.error(">>>>>> Error: " + e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
	}

	@PostMapping("/retry")
	@ResponseBody
	public AjaxResult retry(String ids) {
		try {
			nhapXuatService.retry(ids);
			AjaxResult ajaxResult = AjaxResult.success();
			return ajaxResult;
		} catch (Exception e) {
			logger.error(">>>>>> Error: " + e.getMessage());
			return AjaxResult.error(e.getMessage());
		}
	}

}
