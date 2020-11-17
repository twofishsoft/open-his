package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.OrderBackfeeDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.OrderBackfeeService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【退费主表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "退费主表数据接口",tags = "退费主表数据接口")
@RequestMapping("/system/orderBackfee/")
public class OrderBackfeeController {

	@Resource
	private OrderBackfeeService orderBackfeeService;

	/**
	 * 分页查询退费主表
	 * @param orderBackfeeDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询退费主表数据", notes = "分页查询退费主表数据")
	public AjaxResult listForPage(OrderBackfeeDto orderBackfeeDto){
		DataGridView dataGridView = orderBackfeeService.listPage(orderBackfeeDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加退费主表数据
	 * @param orderBackfeeDto
	 * @return
	 */
	@PostMapping("addOrderBackfee")
	@ApiOperation(value = "添加退费主表数据", notes = "添加退费主表数据")
	public AjaxResult addDictData(@CurrUser OrderBackfeeDto orderBackfeeDto){
		return AjaxResult.toAjax(orderBackfeeService.insert(orderBackfeeDto));
	}

	/**
	 * 更新退费主表数据
	 * @param orderBackfeeDto
	 * @return
	 */
	@PutMapping("updateOrderBackfee")
	@ApiOperation(value = "更新退费主表数据", notes = "更新退费主表数据")
	public AjaxResult updateUser(@CurrUser OrderBackfeeDto orderBackfeeDto){
		return AjaxResult.toAjax(orderBackfeeService.update(orderBackfeeDto));
	}

	/**
	 * 删除退费主表数据(可批量删除)
	 * @param orderBackfeeIds
	 * @return
	 */
	@DeleteMapping("deleteOrderBackfeeByIds/{orderBackfeeIds}")
	@ApiOperation(value = "根据ID退费主表数据", notes = "根据ID删除退费主表数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] orderBackfeeIds){
		return AjaxResult.toAjax(orderBackfeeService.deleteByIds(orderBackfeeIds));
	}

	/**
	 * 根据ID查询退费主表数据
	 * @param {orderBackfeeId
	 * @return
	 */
	@GetMapping("getOrderBackfeeById/{orderBackfeeId}")
	@ApiOperation(value = "根据ID查询退费主表数据", notes = "根据ID查询退费主表数据")
	public AjaxResult getOne(@PathVariable Long orderBackfeeId){
		return AjaxResult.success("查询成功", orderBackfeeService.getOneById(orderBackfeeId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllOrderBackfee")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllOrderBackfee() {
		return AjaxResult.success(orderBackfeeService.selectAll());
	}

}
