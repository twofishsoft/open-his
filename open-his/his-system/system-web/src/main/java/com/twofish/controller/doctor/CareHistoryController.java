package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.CareHistoryDto;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareHistoryService;
import twofish.service.CareOrderItemService;
import twofish.service.RegistrationService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【病例表】控制器
 * @date 2020-11-17 16:30:29
 **/
@RestController
@Log4j2
@Api(value = "病例表数据接口",tags = "病例表数据接口")
@RequestMapping("/doctor/care/")
public class CareHistoryController {

	@Resource
	private CareHistoryService careHistoryService;
	@Resource
	private RegistrationService registrationService;
	@Resource
	private CareOrderItemService careOrderItemService;

	/**
	 * 分页查询病例表
	 * @param careHistoryDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询病例表数据", notes = "分页查询病例表数据")
	public AjaxResult listForPage(CareHistoryDto careHistoryDto){
		DataGridView dataGridView = careHistoryService.listPage(careHistoryDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 保存病例信息
	 * @param careHistoryDto
	 * @return
	 */
	@PostMapping("saveCareHistory")
	@ApiOperation(value = "保存病例信息", notes = "保存病例信息")
	public AjaxResult saveCareHistory(@CurrUser CareHistoryDto careHistoryDto){
		return AjaxResult.toAjax(careHistoryService.insert(careHistoryDto));
	}

	/**
	 * 更新病例表数据
	 * @param careHistoryDto
	 * @return
	 */
	@PutMapping("updateCareHistory")
	@ApiOperation(value = "更新病例表数据", notes = "更新病例表数据")
	public AjaxResult updateCareHistory(@CurrUser CareHistoryDto careHistoryDto){
		return AjaxResult.toAjax(careHistoryService.update(careHistoryDto));
	}

	/**
	 * 删除病例表数据(可批量删除)
	 * @param careHistoryIds
	 * @return
	 */
	@DeleteMapping("deleteCareHistoryByIds/{careHistoryIds}")
	@ApiOperation(value = "根据ID病例表数据", notes = "根据ID删除病例表数据")
	public AjaxResult deleteCareHistoryByIds(@PathVariable Long[] careHistoryIds){
		return AjaxResult.toAjax(careHistoryService.deleteByIds(careHistoryIds));
	}

	/**
	 * 根据处方详情ID删除处方详情【只能删除未支付的】
	 * @param itemId
	 * @return
	 */
	@DeleteMapping("deleteCareOrderItemById/{itemId}")
	@ApiOperation(value = "根据ID病例表数据", notes = "根据ID删除病例表数据")
	public AjaxResult deleteCareOrderItemById(@PathVariable String itemId){
		return AjaxResult.toAjax(careOrderItemService.deleteCareOrderItemById(itemId));
	}

	/**
	 * 根据挂号ID查询病历信息
	 * @param regId
	 * @return
	 */
	@GetMapping("getCareHistoryByRegId/{regId}")
	@ApiOperation(value = "根据挂号ID查询病历信息", notes = "根据挂号ID查询病历信息")
	public AjaxResult getOne(@PathVariable String regId){
		return AjaxResult.success("查询成功", careHistoryService.findById(regId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllCareHistory")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllCareHistory() {
		return AjaxResult.success(careHistoryService.selectAll());
	}

	/**
	 * 查询待就诊的挂号信息
	 * @param scheudlingType
	 * @return
	 */
	@GetMapping("queryToBeSeenRegistration/{scheudlingType}")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult queryToBeSeenRegistration(@PathVariable String scheudlingType) {
		return AjaxResult.success(registrationService.queryRegistrationByScheudlingType(scheudlingType));
	}

	/**
	 * 查询就诊中的挂号信息
	 * @param scheudlingType
	 * @return
	 */
	@GetMapping("queryVisitingRegistration/{scheudlingType}")
	@ApiOperation(value = "查询就诊中的挂号信息", notes = "查询就诊中的挂号信息")
	public AjaxResult queryVisitingRegistration(@PathVariable String scheudlingType) {
		return AjaxResult.success(registrationService.queryRegistrationByScheudlingType(scheudlingType));
	}

	/**
	 * 查询就诊完成的挂号信息
	 * @param scheudlingType
	 * @return
	 */
	@GetMapping("queryVisitCompletedRegistration/{scheudlingType}")
	@ApiOperation(value = "查询就诊完成的挂号信息", notes = "查询就诊完成的挂号信息")
	public AjaxResult queryVisitCompletedRegistration(@PathVariable String scheudlingType) {
		return AjaxResult.success(registrationService.queryRegistrationByScheudlingType(scheudlingType));
	}

	/**
	 * 接诊
	 * @param regId
	 * @return
	 */
	@PostMapping("receivePatient/{regId}")
	@ApiOperation(value = "接诊", notes = "接诊")
	public AjaxResult receivePatient(@PathVariable String regId){
		return AjaxResult.toAjax(registrationService.receivePatient(regId));
	}

	/**
	 * 根据患者ID获取患者信息、档案信息、病历信息
	 * @param patientId
	 * @return
	 */
	@GetMapping("getPatientAllMessageByPatientId/{patientId}")
	@ApiOperation(value = "根据患者ID获取患者信息、档案信息、病历信息", notes = "根据患者ID获取患者信息、档案信息、病历信息")
	public AjaxResult getPatientAllMessageByPatientId(@PathVariable String patientId){
		return AjaxResult.success("查询成功", careHistoryService.getPatientAllMessageByPatientIds(patientId));
	}

	/**
	 * 根据病例ID查询处方列表及详情
	 * @param chId
	 * @return
	 */
	@GetMapping("queryCareOrdersByChId/{chId}")
	@ApiOperation(value = "根据病例ID查询处方列表及详情", notes = "根据病例ID查询处方列表及详情")
	public AjaxResult queryCareOrdersByChId(@PathVariable String chId){
		return AjaxResult.success("查询成功", careHistoryService.queryCareOrdersByChId(chId));
	}

	/**
	 * 保存处方及详情信息
	 * @param careOrderItemDto
	 * @return
	 */
	@PostMapping("saveCareOrderItem")
	@ApiOperation(value = "保存处方及详情信息", notes = "保存处方及详情信息")
	public AjaxResult saveCareOrderItem(@RequestBody CareOrderItemDto careOrderItemDto){
		careOrderItemDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
		return AjaxResult.toAjax(careOrderItemService.saveCareOrderItem(careOrderItemDto));
	}

	/**
	 * 完成就诊
	 * @param regId
	 * @return
	 */
	@PostMapping("visitComplete/{regId}")
	@ApiOperation(value = "完成就诊", notes = "完成就诊")
	public AjaxResult visitComplete(@PathVariable String regId){
		return AjaxResult.toAjax(registrationService.visitComplete(regId));
	}

}
