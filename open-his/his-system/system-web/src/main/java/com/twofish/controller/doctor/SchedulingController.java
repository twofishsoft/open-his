package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.domain.Scheduling;
import com.twofish.domain.SimpleUser;
import com.twofish.dto.SchedulingDto;
import com.twofish.dto.SchedulingInfoDto;
import com.twofish.service.UserService;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.utils.WeekUtil;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.SchedulingService;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ww
 * @description 【排班信息表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "排班信息表数据接口",tags = "排班信息表数据接口")
@RequestMapping("/doctor/scheduling/")
public class SchedulingController {

	@Resource
	private SchedulingService schedulingService;
	@Resource
	private UserService userService;

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
	 * 保存排班信息
	 * @param schedulingDto
	 * @return
	 */
	@PostMapping("saveScheduling")
	@ApiOperation(value = "保存排班信息", notes = "保存排班信息")
	public AjaxResult saveScheduling(@RequestBody SchedulingDto schedulingDto){
		return AjaxResult.toAjax(schedulingService.saveScheduling(schedulingDto));
	}

	/**
	 * 更新排班信息表数据
	 * @param schedulingDto
	 * @return
	 */
	@PutMapping("updateScheduling")
	@ApiOperation(value = "更新排班信息表数据", notes = "更新排班信息表数据")
	public AjaxResult updateScheduling(@CurrUser SchedulingDto schedulingDto){
		return AjaxResult.toAjax(schedulingService.update(schedulingDto));
	}

	/**
	 * 删除排班信息表数据(可批量删除)
	 * @param schedulingIds
	 * @return
	 */
	@DeleteMapping("deleteSchedulingByIds/{schedulingIds}")
	@ApiOperation(value = "根据ID排班信息表数据", notes = "根据ID删除排班信息表数据")
	public AjaxResult deleteSchedulingByIds(@PathVariable Long[] schedulingIds){
		return AjaxResult.toAjax(schedulingService.deleteByIds(schedulingIds));
	}

	/**
	 * 根据ID查询排班信息表数据
	 * @param schedulingId
	 * @return
	 */
	@GetMapping("getSchedulingById/{schedulingId}")
	@ApiOperation(value = "根据ID查询排班信息表数据", notes = "根据ID查询排班信息表数据")
	public AjaxResult getOne(@PathVariable String schedulingId){
		return AjaxResult.success("查询成功", schedulingService.findById(schedulingId));
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

	/**
	 * 查询要排班的医生信息
	 * @return
	 */
	@GetMapping("queryUsersNeedScheduling")
	@ApiOperation(value = "查询要排班的医生信息", notes = "查询要排班的医生信息")
	public AjaxResult queryUsersNeedScheduling(SchedulingDto schedulingDto) {
		Long deptId = Long.parseLong(schedulingDto.getDeptId().toString());
		return AjaxResult.success(userService.getUsersNeedScheduling(deptId));
	}

	/**
	 * 查询要排班的医生的排班信息
	 * @return
	 */
	@GetMapping("queryScheduling")
	@ApiOperation(value = "查询要排班的医生的排班信息", notes = "查询要排班的医生的排班信息")
	public AjaxResult queryScheduling(SchedulingDto schedulingDto) {
		SchedulingInfoDto schedulingInfoDto = schedulingService.queryScheduling(schedulingDto);
		List<String> schedulingDay = schedulingInfoDto.getSchedulingDay();
		List<String> labelNames = new ArrayList<>();
		schedulingDay.forEach(item -> {
			labelNames.add(item + "(" + WeekUtil.dateToWeek(item) + ")");
		});
		AjaxResult success = AjaxResult.success();
		success.put("labelNames", labelNames);
		success.put("schedulingData", schedulingInfoDto.getSchedulingData());
		success.put("tableData", schedulingInfoDto.getTableData());
		return success;
	}

	/**
	 * 查询当前登陆用户的排班信息
	 * @return
	 */
	@GetMapping("queryMyScheduling")
	@ApiOperation(value = "查询当前登陆用户的排班信息", notes = "查询当前登陆用户的排班信息")
	public AjaxResult queryMyScheduling(SchedulingDto schedulingDto) {
		SimpleUser currentSimpleUser = ShiroSecurityUtils.getCurrentSimpleUser();
		Long userId = (Long) currentSimpleUser.getUserId();
		schedulingDto.setUserId(userId);
		SchedulingInfoDto schedulingInfoDto = schedulingService.queryMyScheduling(schedulingDto);
		List<String> schedulingDay = schedulingInfoDto.getSchedulingDay();
		List<String> labelNames = new ArrayList<>();
		schedulingDay.forEach(item -> {
			labelNames.add(item + "(" + WeekUtil.dateToWeek(item) + ")");
		});
		AjaxResult success = AjaxResult.success();
		success.put("labelNames", labelNames);
		success.put("schedulingData", schedulingInfoDto.getSchedulingData());
		success.put("tableData", schedulingInfoDto.getTableData());
		return success;
	}



}
