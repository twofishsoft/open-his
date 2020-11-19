package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.CareOrderDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareOrderService;
import twofish.service.RegistrationService;

import javax.annotation.Resource;

/**
 * @author ww
 * @description 【药用处方表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "药用处方表数据接口",tags = "药用处方表数据接口")
@RequestMapping("/doctor/careOrder/")
public class CareOrderController {

	@Resource
	private CareOrderService careOrderService;

	/**
	 * 分页查询药用处方表
	 * @param careOrderDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询药用处方表数据", notes = "分页查询药用处方表数据")
	public AjaxResult listForPage(CareOrderDto careOrderDto){
		DataGridView dataGridView = careOrderService.listPage(careOrderDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加药用处方表数据
	 * @param careOrderDto
	 * @return
	 */
	@PostMapping("addCareOrder")
	@ApiOperation(value = "添加药用处方表数据", notes = "添加药用处方表数据")
	public AjaxResult addCareOrder(@CurrUser CareOrderDto careOrderDto){
		return AjaxResult.toAjax(careOrderService.insert(careOrderDto));
	}

	/**
	 * 更新药用处方表数据
	 * @param careOrderDto
	 * @return
	 */
	@PutMapping("updateCareOrder")
	@ApiOperation(value = "更新药用处方表数据", notes = "更新药用处方表数据")
	public AjaxResult updateCareOrder(@CurrUser CareOrderDto careOrderDto){
		return AjaxResult.toAjax(careOrderService.update(careOrderDto));
	}

	/**
	 * 删除药用处方表数据(可批量删除)
	 * @param careOrderIds
	 * @return
	 */
	@DeleteMapping("deleteCareOrderByIds/{careOrderIds}")
	@ApiOperation(value = "根据ID药用处方表数据", notes = "根据ID删除药用处方表数据")
	public AjaxResult deleteCareOrderByIds(@PathVariable Long[] careOrderIds){
		return AjaxResult.toAjax(careOrderService.deleteByIds(careOrderIds));
	}

	/**
	 * 根据ID查询药用处方表数据
	 * @param careOrderId
	 * @return
	 */
	@GetMapping("getCareOrderById/{careOrderId}")
	@ApiOperation(value = "根据ID查询药用处方表数据", notes = "根据ID查询药用处方表数据")
	public AjaxResult getOne(@PathVariable String careOrderId){
		return AjaxResult.success("查询成功", careOrderService.findById(careOrderId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllCareOrder")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllCareOrder() {
		return AjaxResult.success(careOrderService.selectAll());
	}

}
