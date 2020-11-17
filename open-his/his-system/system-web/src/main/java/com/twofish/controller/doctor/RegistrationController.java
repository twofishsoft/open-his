package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.constants.Constants;
import com.twofish.domain.Registration;
import com.twofish.dto.OrderChargeDto;
import com.twofish.dto.RegistrationDto;
import com.twofish.dto.SchedulingDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.OrderChargeService;
import twofish.service.PatientService;
import twofish.service.RegistrationService;
import twofish.service.SchedulingService;

import javax.annotation.Resource;

/**
 * @author ww
 * @description 【挂号信息】控制器
 * @date 2020-11-17 15:03:48
 **/
@RestController
@Log4j2
@Api(value = "挂号信息数据接口",tags = "挂号信息数据接口")
@RequestMapping("/doctor/registration/")
public class RegistrationController {

	@Resource
	private RegistrationService registrationService;
	@Resource
	private PatientService patientService;
	@Resource
	private SchedulingService schedulingService;
	@Resource
	private OrderChargeService orderChargeService;

	/**
	 * 分页查询挂号信息数据
	 * @param registrationDto
	 * @return
	 */
	@GetMapping("queryRegistrationForPage")
	@ApiOperation(value = "分页查询挂号信息数据", notes = "分页查询挂号信息数据")
	public AjaxResult queryRegistrationForPage(RegistrationDto registrationDto){
		DataGridView dataGridView = registrationService.listPage(registrationDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加患者PC端挂号
	 * @param registrationDto
	 * @return
	 */
	@PostMapping("addRegistration")
	@ApiOperation(value = "添加患者PC端挂号", notes = "添加患者PC端挂号")
	public AjaxResult addDictData(@CurrUser RegistrationDto registrationDto){
		return AjaxResult.toAjax(registrationService.insert(registrationDto));
	}

	/**
	 * 更新挂号信息数据
	 * @param registrationDto
	 * @return
	 */
	@PutMapping("updateRegistration")
	@ApiOperation(value = "更新挂号信息数据", notes = "更新挂号信息数据")
	public AjaxResult updateUser(@CurrUser RegistrationDto registrationDto){
		return AjaxResult.toAjax(registrationService.update(registrationDto));
	}

	/**
	 * 删除挂号信息数据(可批量删除)
	 * @param registrationIds
	 * @return
	 */
	@DeleteMapping("deleteRegistrationByIds/{registrationIds}")
	@ApiOperation(value = "根据ID挂号信息数据", notes = "根据ID删除挂号信息数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] registrationIds){
		return AjaxResult.toAjax(registrationService.deleteByIds(registrationIds));
	}

	/**
	 * 根据ID查询挂号信息数据
	 * @param registrationId
	 * @return
	 */
	@GetMapping("getRegistrationById/{registrationId}")
	@ApiOperation(value = "根据ID查询挂号信息数据", notes = "根据ID查询挂号信息数据")
	public AjaxResult getOne(@PathVariable Long registrationId){
		return AjaxResult.success("查询成功", registrationService.getOneById(registrationId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllRegistration")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllUser() {
		return AjaxResult.success(registrationService.selectAll());
	}

	/**
	 * 根据条件查询有号的部门
	 * @param schedulingDto
	 * @return
	 */
	@GetMapping("listDeptForScheduling")
	@ApiOperation(value = "根据条件查询有号的部门", notes = "根据条件查询有号的部门")
	public AjaxResult listDeptForScheduling(SchedulingDto schedulingDto) {
		return AjaxResult.success(schedulingService.listDeptForScheduling(schedulingDto));
	}

	/**
	 * 根据条件查询有号的部门
	 * @param idCard
	 * @return
	 */
	@GetMapping("getPatientByIdCard/{idCard}")
	@ApiOperation(value = "根据身份证号查询患者信息", notes = "根据身份证号查询患者信息")
	public AjaxResult getPatientByIdCard(@PathVariable String idCard) {
		return AjaxResult.success(patientService.getPatientByIdCard(idCard));
	}

	/**
	 * 挂号收费
	 * @param regId
	 * @return
	 */
	@PostMapping("collectFee/{regId}")
	@ApiOperation(value = "挂号收费", notes = "挂号收费")
	public AjaxResult collectFee(@PathVariable Long regId, @CurrUser OrderChargeDto orderChargeDto) {
		return AjaxResult.toAjax(orderChargeService.collectFee(regId, orderChargeDto));
	}

	/**
	 * 作废【根据挂号单号】
	 * @param regId
	 * @return
	 */
	@PostMapping("doInvalid/{regId}")
	@ApiOperation(value = "单号作废", notes = "单号作废")
	public AjaxResult doInvalid(@PathVariable Long regId) {
		return AjaxResult.toAjax(registrationService.doInvalid(regId));
	}

	/**
	 * 退号【根据挂号单号】
	 * @param regId
	 * @return
	 */
	@PostMapping("doReturn/{regId}")
	@ApiOperation(value = "单号作废", notes = "单号作废")
	public AjaxResult doReturn(@PathVariable Long regId) {
		return AjaxResult.toAjax(registrationService.doReturn(regId));
	}

}
