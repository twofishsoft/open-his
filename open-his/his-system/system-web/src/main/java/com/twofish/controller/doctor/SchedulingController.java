package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.SchedulingDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.SchedulingService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【排班信息表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "排班信息表数据接口",tags = "排班信息表数据接口")
@RequestMapping("/system/scheduling/")
public class SchedulingController {

	@Resource
	private SchedulingService schedulingService;

	/**
	 * 分页查询排班信息表
	 * @param schedulingDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询排班信息表数据", notes = "分页查询排班信息表数据")
	public AjaxResult listForPage(SchedulingDto schedulingDto){
		DataGridView dataGridView = schedulingService.listPage(schedulingDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加排班信息表数据
	 * @param schedulingDto
	 * @return
	 */
	@PostMapping("addScheduling")
	@ApiOperation(value = "添加排班信息表数据", notes = "添加排班信息表数据")
	public AjaxResult addDictData(@CurrUser SchedulingDto schedulingDto){
		return AjaxResult.toAjax(schedulingService.insert(schedulingDto));
	}

	/**
	 * 更新排班信息表数据
	 * @param schedulingDto
	 * @return
	 */
	@PutMapping("updateScheduling")
	@ApiOperation(value = "更新排班信息表数据", notes = "更新排班信息表数据")
	public AjaxResult updateUser(@CurrUser SchedulingDto schedulingDto){
		return AjaxResult.toAjax(schedulingService.update(schedulingDto));
	}

	/**
	 * 删除排班信息表数据(可批量删除)
	 * @param schedulingIds
	 * @return
	 */
	@DeleteMapping("deleteSchedulingByIds/{schedulingIds}")
	@ApiOperation(value = "根据ID排班信息表数据", notes = "根据ID删除排班信息表数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] schedulingIds){
		return AjaxResult.toAjax(schedulingService.deleteByIds(schedulingIds));
	}

	/**
	 * 根据ID查询排班信息表数据
	 * @param {schedulingId
	 * @return
	 */
	@GetMapping("getSchedulingById/{schedulingId}")
	@ApiOperation(value = "根据ID查询排班信息表数据", notes = "根据ID查询排班信息表数据")
	public AjaxResult getOne(@PathVariable Long schedulingId){
		return AjaxResult.success("查询成功", schedulingService.getOneById(schedulingId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllScheduling")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllScheduling() {
		return AjaxResult.success(schedulingService.selectAll());
	}

}
