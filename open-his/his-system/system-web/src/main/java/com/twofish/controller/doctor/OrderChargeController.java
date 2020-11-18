package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderChargeItem;
import com.twofish.dto.OrderChargeDto;
import com.twofish.dto.OrderChargeWithCashDto;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.OrderChargeItemService;
import twofish.service.OrderChargeService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【收费表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "收费表数据接口",tags = "收费表数据接口")
@RequestMapping("/doctor/charge/")
public class OrderChargeController {

	@Resource
	private OrderChargeService orderChargeService;
	@Resource
	private OrderChargeItemService orderChargeItemService;

	/**
	 * 分页查询所有收费单
	 * @param orderChargeDto
	 * @return
	 */
	@GetMapping("queryAllOrderChargeForPage")
	@ApiOperation(value = "分页查询所有收费单", notes = "分页查询所有收费单")
	public AjaxResult listForPage(OrderChargeDto orderChargeDto){
		DataGridView dataGridView = orderChargeService.listPage(orderChargeDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加收费表数据
	 * @param orderChargeDto
	 * @return
	 */
	@PostMapping("addOrderCharge")
	@ApiOperation(value = "添加收费表数据", notes = "添加收费表数据")
	public AjaxResult addOrderCharge(@CurrUser OrderChargeDto orderChargeDto){
		return AjaxResult.toAjax(orderChargeService.insert(orderChargeDto));
	}

	/**
	 * 更新收费表数据
	 * @param orderChargeDto
	 * @return
	 */
	@PutMapping("updateOrderCharge")
	@ApiOperation(value = "更新收费表数据", notes = "更新收费表数据")
	public AjaxResult updateOrderCharge(@CurrUser OrderChargeDto orderChargeDto){
		return AjaxResult.toAjax(orderChargeService.update(orderChargeDto));
	}

	/**
	 * 删除收费表数据(可批量删除)
	 * @param orderChargeIds
	 * @return
	 */
	@DeleteMapping("deleteOrderChargeByIds/{orderChargeIds}")
	@ApiOperation(value = "根据ID收费表数据", notes = "根据ID删除收费表数据")
	public AjaxResult deleteOrderChargeByIds(@PathVariable Long[] orderChargeIds){
		return AjaxResult.toAjax(orderChargeService.deleteByIds(orderChargeIds));
	}

	/**
	 * 根据ID查询收费表数据
	 * @param orderChargeId
	 * @return
	 */
	@GetMapping("getOrderChargeById/{orderChargeId}")
	@ApiOperation(value = "根据ID查询收费表数据", notes = "根据ID查询收费表数据")
	public AjaxResult getOne(@PathVariable String orderChargeId){
		return AjaxResult.success("查询成功", orderChargeService.findById(orderChargeId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllOrderCharge")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllOrderCharge() {
		return AjaxResult.success(orderChargeService.selectAll());
	}

	/**
	 * 根据挂号ID查询未支付的处方信息及详情
	 * @return
	 */
	@GetMapping("getNoChargeCareHistoryByRegId/{regId}")
	@ApiOperation(value = "根据挂号ID查询未支付的处方信息及详情", notes = "根据挂号ID查询未支付的处方信息及详情")
	public AjaxResult getNoChargeCareHistoryByRegId(@PathVariable String regId) {
		return AjaxResult.success(orderChargeService.getNoChargeCareHistoryByRegId(regId));
	}

	/**
	 * 创建现金收费订单
	 * @param orderChargeWithCashDto
	 * @return
	 */
	@PostMapping("createOrderChargeWithCash")
	@ApiOperation(value = "创建现金收费订单", notes = "创建现金收费订单")
	public AjaxResult createOrderChargeWithCash(@RequestBody OrderChargeWithCashDto orderChargeWithCashDto){
		orderChargeWithCashDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
		return AjaxResult.success(orderChargeService.createOrderChargeWithCash(orderChargeWithCashDto, Constants.PAY_TYPE_0));
	}

	/**
	 * 创建支付宝收费订单
	 * @param orderChargeWithCashDto
	 * @return
	 */
	@PostMapping("createOrderChargeWithZfb")
	@ApiOperation(value = "创建支付宝收费订单", notes = "创建支付宝收费订单")
	public AjaxResult createOrderChargeWithZfb(@RequestBody OrderChargeWithCashDto orderChargeWithCashDto){
		orderChargeWithCashDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
		return AjaxResult.success(orderChargeService.createOrderChargeWithCash(orderChargeWithCashDto, Constants.PAY_TYPE_1));
	}

	/**
	 * 根据订单ID查询订单信息【验证是否支付成功】
	 * @param orderId
	 * @return
	 */
	@GetMapping("queryOrderChargeOrderId/{orderId}")
	@ApiOperation(value = "创建支付宝收费订单", notes = "创建支付宝收费订单")
	public AjaxResult queryOrderChargeOrderId(@PathVariable String orderId){
		return AjaxResult.success(orderChargeService.findById(orderId));
	}

	/**
	 * 根据收费单的ID查询收费详情信息
	 * @param orderId
	 * @return
	 */
	@GetMapping("queryOrderChargeItemByOrderId/{orderId}")
	@ApiOperation(value = "根据收费单的ID查询收费详情信息", notes = "根据收费单的ID查询收费详情信息")
	public AjaxResult queryOrderChargeItemByOrderId(@PathVariable String orderId){
		return AjaxResult.success(orderChargeItemService.queryByAttrList(OrderChargeItem.COL_ORDER_ID, orderId));
	}

	/**
	 * 订单列表现金支付订单
	 * @param orderId
	 * @return
	 */
	@GetMapping("payWithCash/{orderId}")
	@ApiOperation(value = "订单列表现金支付订单", notes = "订单列表现金支付订单")
	public AjaxResult payWithCash(@PathVariable String orderId){
		return AjaxResult.success(orderChargeService.payWithCash(orderId, Constants.PAY_TYPE_0));
	}

	/**
	 * 订单列表支付宝支付订单
	 * @param orderId
	 * @return
	 */
	@GetMapping("toPayOrderWithZfb/{orderId}")
	@ApiOperation(value = "订单列表支付宝支付订单", notes = "订单列表支付宝支付订单")
	public AjaxResult toPayOrderWithZfb(@PathVariable String orderId){
		return AjaxResult.success(orderChargeService.payWithCash(orderId, Constants.PAY_TYPE_1));
	}

}
