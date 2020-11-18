package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.PatientFileDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.PatientFileService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【患者档案】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "患者档案数据接口",tags = "患者档案数据接口")
@RequestMapping("/doctor/patientFile/")
public class PatientFileController {

	@Resource
	private PatientFileService patientFileService;

	/**
	 * 分页查询患者档案
	 * @param patientFileDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询患者档案数据", notes = "分页查询患者档案数据")
	public AjaxResult listForPage(PatientFileDto patientFileDto){
		DataGridView dataGridView = patientFileService.listPage(patientFileDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加患者档案数据
	 * @param patientFileDto
	 * @return
	 */
	@PostMapping("addPatientFile")
	@ApiOperation(value = "添加患者档案数据", notes = "添加患者档案数据")
	public AjaxResult addPatientFile(@CurrUser PatientFileDto patientFileDto){
		return AjaxResult.toAjax(patientFileService.insert(patientFileDto));
	}

	/**
	 * 更新患者档案数据
	 * @param patientFileDto
	 * @return
	 */
	@PutMapping("updatePatientFile")
	@ApiOperation(value = "更新患者档案数据", notes = "更新患者档案数据")
	public AjaxResult updatePatientFile(@CurrUser PatientFileDto patientFileDto){
		return AjaxResult.toAjax(patientFileService.update(patientFileDto));
	}

	/**
	 * 删除患者档案数据(可批量删除)
	 * @param patientFileIds
	 * @return
	 */
	@DeleteMapping("deletePatientFileByIds/{patientFileIds}")
	@ApiOperation(value = "根据ID患者档案数据", notes = "根据ID删除患者档案数据")
	public AjaxResult deletePatientFileByIds(@PathVariable Long[] patientFileIds){
		return AjaxResult.toAjax(patientFileService.deleteByIds(patientFileIds));
	}

	/**
	 * 根据ID查询患者档案数据
	 * @param patientFileId
	 * @return
	 */
	@GetMapping("getPatientFileById/{patientFileId}")
	@ApiOperation(value = "根据ID查询患者档案数据", notes = "根据ID查询患者档案数据")
	public AjaxResult getOne(@PathVariable String patientFileId){
		return AjaxResult.success("查询成功", patientFileService.findById(patientFileId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllPatientFile")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllPatientFile() {
		return AjaxResult.success(patientFileService.selectAll());
	}

}
