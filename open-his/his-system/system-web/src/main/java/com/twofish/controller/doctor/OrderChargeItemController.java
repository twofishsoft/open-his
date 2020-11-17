package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.OrderChargeItemDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.OrderChargeItemService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【支付订单详情表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "支付订单详情表数据接口",tags = "支付订单详情表数据接口")
@RequestMapping("/doctor/orderChargeItem/")
public class OrderChargeItemController {

	@Resource
	private OrderChargeItemService orderChargeItemService;

	/**
	 * 分页查询支付订单详情表
	 * @param orderChargeItemDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询支付订单详情表数据", notes = "分页查询支付订单详情表数据")
	public AjaxResult listForPage(OrderChargeItemDto orderChargeItemDto){
		DataGridView dataGridView = orderChargeItemService.listPage(orderChargeItemDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加支付订单详情表数据
	 * @param orderChargeItemDto
	 * @return
	 */
	@PostMapping("addOrderChargeItem")
	@ApiOperation(value = "添加支付订单详情表数据", notes = "添加支付订单详情表数据")
	public AjaxResult addDictData(@CurrUser OrderChargeItemDto orderChargeItemDto){
		return AjaxResult.toAjax(orderChargeItemService.insert(orderChargeItemDto));
	}

	/**
	 * 更新支付订单详情表数据
	 * @param orderChargeItemDto
	 * @return
	 */
	@PutMapping("updateOrderChargeItem")
	@ApiOperation(value = "更新支付订单详情表数据", notes = "更新支付订单详情表数据")
	public AjaxResult updateUser(@CurrUser OrderChargeItemDto orderChargeItemDto){
		return AjaxResult.toAjax(orderChargeItemService.update(orderChargeItemDto));
	}

	/**
	 * 删除支付订单详情表数据(可批量删除)
	 * @param orderChargeItemIds
	 * @return
	 */
	@DeleteMapping("deleteOrderChargeItemByIds/{orderChargeItemIds}")
	@ApiOperation(value = "根据ID支付订单详情表数据", notes = "根据ID删除支付订单详情表数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] orderChargeItemIds){
		return AjaxResult.toAjax(orderChargeItemService.deleteByIds(orderChargeItemIds));
	}

	/**
	 * 根据ID查询支付订单详情表数据
	 * @param {orderChargeItemId
	 * @return
	 */
	@GetMapping("getOrderChargeItemById/{orderChargeItemId}")
	@ApiOperation(value = "根据ID查询支付订单详情表数据", notes = "根据ID查询支付订单详情表数据")
	public AjaxResult getOne(@PathVariable Long orderChargeItemId){
		return AjaxResult.success("查询成功", orderChargeItemService.getOneById(orderChargeItemId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllOrderChargeItem")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllOrderChargeItem() {
		return AjaxResult.success(orderChargeItemService.selectAll());
	}

}
