package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.CareHistoryDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareHistoryService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【病例表】控制器
 * @date 2020-11-17 16:30:29
 **/
@RestController
@Log4j2
@Api(value = "病例表数据接口",tags = "病例表数据接口")
@RequestMapping("/system/careHistory/")
public class CareHistoryController {

	@Resource
	private CareHistoryService careHistoryService;

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
	 * 添加病例表数据
	 * @param careHistoryDto
	 * @return
	 */
	@PostMapping("addCareHistory")
	@ApiOperation(value = "添加病例表数据", notes = "添加病例表数据")
	public AjaxResult addDictData(@CurrUser CareHistoryDto careHistoryDto){
		return AjaxResult.toAjax(careHistoryService.insert(careHistoryDto));
	}

	/**
	 * 更新病例表数据
	 * @param careHistoryDto
	 * @return
	 */
	@PutMapping("updateCareHistory")
	@ApiOperation(value = "更新病例表数据", notes = "更新病例表数据")
	public AjaxResult updateUser(@CurrUser CareHistoryDto careHistoryDto){
		return AjaxResult.toAjax(careHistoryService.update(careHistoryDto));
	}

	/**
	 * 删除病例表数据(可批量删除)
	 * @param careHistoryIds
	 * @return
	 */
	@DeleteMapping("deleteCareHistoryByIds/{careHistoryIds}")
	@ApiOperation(value = "根据ID病例表数据", notes = "根据ID删除病例表数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] careHistoryIds){
		return AjaxResult.toAjax(careHistoryService.deleteByIds(careHistoryIds));
	}

	/**
	 * 根据ID查询病例表数据
	 * @param careHistoryId
	 * @return
	 */
	@GetMapping("getCareHistoryById/{careHistoryId}")
	@ApiOperation(value = "根据ID查询病例表数据", notes = "根据ID查询病例表数据")
	public AjaxResult getOne(@PathVariable Long careHistoryId){
		return AjaxResult.success("查询成功", careHistoryService.getOneById(careHistoryId));
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

}
