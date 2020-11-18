package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderBackfeeItem;
import com.twofish.domain.SimpleUser;
import com.twofish.dto.OrderBackfeeDto;
import com.twofish.dto.OrderBackfeeWithCashDto;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.*;

import javax.annotation.Resource;

/**
 * @author ww
 * @description 【退费主表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "退费主表数据接口",tags = "退费主表数据接口")
@RequestMapping("/doctor/backfee/")
public class OrderBackfeeController {

	@Resource
	private OrderBackfeeService orderBackfeeService;
	@Resource
	private OrderBackfeeItemService orderBackfeeItemService;

	/**
	 * 分页面查询所有退费订单
	 * @param orderBackfeeDto
	 * @return
	 */
	@GetMapping("queryAllOrderBackfeeForPage")
	@ApiOperation(value = "分页面查询所有退费订单", notes = "分页面查询所有退费订单")
	public AjaxResult listForPage(OrderBackfeeDto orderBackfeeDto){
		DataGridView dataGridView = orderBackfeeService.listPage(orderBackfeeDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 更新退费主表数据
	 * @param orderBackfeeDto
	 * @return
	 */
	@PutMapping("updateOrderBackfee")
	@ApiOperation(value = "更新退费主表数据", notes = "更新退费主表数据")
	public AjaxResult updateOrderBackfee(@CurrUser OrderBackfeeDto orderBackfeeDto){
		return AjaxResult.toAjax(orderBackfeeService.update(orderBackfeeDto));
	}

	/**
	 * 删除退费主表数据(可批量删除)
	 * @param orderBackfeeIds
	 * @return
	 */
	@DeleteMapping("deleteOrderBackfeeByIds/{orderBackfeeIds}")
	@ApiOperation(value = "根据ID退费主表数据", notes = "根据ID删除退费主表数据")
	public AjaxResult deleteOrderBackfeeByIds(@PathVariable Long[] orderBackfeeIds){
		return AjaxResult.toAjax(orderBackfeeService.deleteByIds(orderBackfeeIds));
	}

	/**
	 * 根据ID查询退费主表数据
	 * @param orderBackfeeId
	 * @return
	 */
	@GetMapping("queryOrderBackfeeByBackId/{backId}")
	@ApiOperation(value = "根据ID查询退费主表数据", notes = "根据ID查询退费主表数据")
	public AjaxResult getOne(@PathVariable String orderBackfeeId){
		return AjaxResult.success("查询成功", orderBackfeeService.findById(orderBackfeeId));
	}

	/**
	 * 根据退费单ID查询退费单详情
	 * @param backId
	 * @return
	 */
	@GetMapping("queryOrderBackfeeItemByBackId/{backId}")
	@ApiOperation(value = "根据退费单ID查询退费单详情", notes = "根据ID查询退费主表数据")
	public AjaxResult queryOrderBackfeeItemByBackId(@PathVariable Long backId){
		return AjaxResult.success("查询成功", orderBackfeeItemService.queryByAttrList(OrderBackfeeItem.COL_BACK_ID, backId));
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

	/**
	 * 根据挂号ID查询已支付的处方信息【退费时使用】
	 * @return
	 */
	@GetMapping("getChargedCareHistoryByRegId/{regId}")
	@ApiOperation(value = "根据挂号ID查询已支付的处方信息【退费时使用】", notes = "根据挂号ID查询已支付的处方信息【退费时使用】")
	public AjaxResult getChargedCareHistoryByRegId(@PathVariable String regId) {
		return AjaxResult.success(orderBackfeeService.getChargedCareHistoryByRegId(regId));
	}

	/**
	 * 创建现金退费订单
	 * @param orderBackfeeDto
	 * @return
	 */
	@PostMapping("createOrderBackfeeWithCash")
	@ApiOperation(value = "创建现金退费订单", notes = "创建现金退费订单")
	public AjaxResult createOrderBackfeeWithCash(@RequestBody OrderBackfeeWithCashDto orderBackfeeDto){
		orderBackfeeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
		return AjaxResult.toAjax(orderBackfeeService.createOrderBackfeeWithCash(orderBackfeeDto, Constants.PAY_TYPE_0));
	}

	/**
	 * 创建支付宝退费订单
	 * @param orderBackfeeDto
	 * @return
	 */
	@PostMapping("createOrderBackfeeWithZfb")
	@ApiOperation(value = "创建支付宝退费订单", notes = "创建支付宝退费订单")
	public AjaxResult createOrderBackfeeWithZfb(@RequestBody OrderBackfeeWithCashDto orderBackfeeDto){
		orderBackfeeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
		return AjaxResult.toAjax(orderBackfeeService.createOrderBackfeeWithCash(orderBackfeeDto, Constants.PAY_TYPE_1));
	}

}
