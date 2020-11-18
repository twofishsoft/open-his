package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareOrderItemService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【开诊细表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "开诊细表数据接口",tags = "开诊细表数据接口")
@RequestMapping("/doctor/careOrderItem/")
public class CareOrderItemController {

	@Resource
	private CareOrderItemService careOrderItemService;

	/**
	 * 分页查询开诊细表
	 * @param careOrderItemDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询开诊细表数据", notes = "分页查询开诊细表数据")
	public AjaxResult listForPage(CareOrderItemDto careOrderItemDto){
		DataGridView dataGridView = careOrderItemService.listPage(careOrderItemDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加开诊细表数据
	 * @param careOrderItemDto
	 * @return
	 */
	@PostMapping("addCareOrderItem")
	@ApiOperation(value = "添加开诊细表数据", notes = "添加开诊细表数据")
	public AjaxResult addCareOrderItem(@CurrUser CareOrderItemDto careOrderItemDto){
		return AjaxResult.toAjax(careOrderItemService.insert(careOrderItemDto));
	}

	/**
	 * 更新开诊细表数据
	 * @param careOrderItemDto
	 * @return
	 */
	@PutMapping("updateCareOrderItem")
	@ApiOperation(value = "更新开诊细表数据", notes = "更新开诊细表数据")
	public AjaxResult updateCareOrderItem(@CurrUser CareOrderItemDto careOrderItemDto){
		return AjaxResult.toAjax(careOrderItemService.update(careOrderItemDto));
	}

	/**
	 * 删除开诊细表数据(可批量删除)
	 * @param careOrderItemIds
	 * @return
	 */
	@DeleteMapping("deleteCareOrderItemByIds/{careOrderItemIds}")
	@ApiOperation(value = "根据ID开诊细表数据", notes = "根据ID删除开诊细表数据")
	public AjaxResult deleteCareOrderItemByIds(@PathVariable Long[] careOrderItemIds){
		return AjaxResult.toAjax(careOrderItemService.deleteByIds(careOrderItemIds));
	}

	/**
	 * 根据ID查询开诊细表数据
	 * @param careOrderItemId
	 * @return
	 */
	@GetMapping("getCareOrderItemById/{careOrderItemId}")
	@ApiOperation(value = "根据ID查询开诊细表数据", notes = "根据ID查询开诊细表数据")
	public AjaxResult getOne(@PathVariable String careOrderItemId){
		return AjaxResult.success("查询成功", careOrderItemService.getOneById(careOrderItemId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllCareOrderItem")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllCareOrderItem() {
		return AjaxResult.success(careOrderItemService.selectAll());
	}

}
