package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.PatientDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareHistoryService;
import twofish.service.PatientFileService;
import twofish.service.PatientService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【患者信息表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "患者信息表数据接口",tags = "患者信息表数据接口")
@RequestMapping("/doctor/patient/")
public class PatientController {

	@Resource
	private PatientService patientService;
	@Resource
	private PatientFileService patientFileService;
	@Resource
	private CareHistoryService careHistoryService;

	/**
	 * 分页查询所有患者信息
	 * @param patientDto
	 * @return
	 */
	@GetMapping("listPatientForPage")
	@ApiOperation(value = "分页查询所有患者信息", notes = "分页查询所有患者信息")
	public AjaxResult listPatientForPage(PatientDto patientDto){
		DataGridView dataGridView = patientService.listPage(patientDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加患者信息表数据
	 * @param patientDto
	 * @return
	 */
	@PostMapping("addPatient")
	@ApiOperation(value = "添加患者信息表数据", notes = "添加患者信息表数据")
	public AjaxResult addPatient(@CurrUser PatientDto patientDto){
		return AjaxResult.toAjax(patientService.insert(patientDto));
	}

	/**
	 * 更新患者信息表数据
	 * @param patientDto
	 * @return
	 */
	@PutMapping("updatePatient")
	@ApiOperation(value = "更新患者信息表数据", notes = "更新患者信息表数据")
	public AjaxResult updatePatient(@CurrUser PatientDto patientDto){
		return AjaxResult.toAjax(patientService.update(patientDto));
	}

	/**
	 * 删除患者信息表数据(可批量删除)
	 * @param patientIds
	 * @return
	 */
	@DeleteMapping("deletePatientByIds/{patientIds}")
	@ApiOperation(value = "根据ID患者信息表数据", notes = "根据ID删除患者信息表数据")
	public AjaxResult deletePatientByIds(@PathVariable Long[] patientIds){
		return AjaxResult.toAjax(patientService.deleteByIds(patientIds));
	}

	/**
	 * 根据ID查询患者信息表数据
	 * @param patientId
	 * @return
	 */
	@GetMapping("getPatientById/{patientId}")
	@ApiOperation(value = "根据ID查询患者信息表数据", notes = "根据ID查询患者信息表数据")
	public AjaxResult getOne(@PathVariable String patientId){
		return AjaxResult.success("查询成功", patientService.getOneById(patientId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllPatient")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllPatient() {
		return AjaxResult.success(patientService.selectAll());
	}

	/**
	 * 根据患者ID查询患者的档案信息
	 * @param patientId
	 * @return
	 */
	@GetMapping("getPatientFileById/{patientId}")
	@ApiOperation(value = "根据患者ID查询患者的档案信息", notes = "根据患者ID查询患者的档案信息")
	public AjaxResult getPatientFileById(@PathVariable String patientId){
		return AjaxResult.success("查询成功", patientFileService.getOneById(patientId));
	}

	/**
	 * 根据患者ID查询患者所有信息【基础，档案，病例】
	 * @param patientId
	 * @return
	 */
	@GetMapping("getPatientAllMessageByPatientId/{patientId}")
	@ApiOperation(value = "根据患者ID查询患者所有信息【基础，档案，病例】", notes = "根据患者ID查询患者所有信息【基础，档案，病例】")
	public AjaxResult getPatientAllMessageByPatientId(@PathVariable Long patientId){
		return AjaxResult.success("查询成功", careHistoryService.getPatientAllMessageByPatientId(patientId));
	}

}
