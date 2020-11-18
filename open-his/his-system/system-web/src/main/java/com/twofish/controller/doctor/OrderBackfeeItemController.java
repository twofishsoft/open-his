package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.OrderBackfeeItemDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.OrderBackfeeItemService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【退费订单详情表】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "退费订单详情表数据接口",tags = "退费订单详情表数据接口")
@RequestMapping("/doctor/orderBackfeeItem/")
public class OrderBackfeeItemController {

	@Resource
	private OrderBackfeeItemService orderBackfeeItemService;

	/**
	 * 分页查询退费订单详情表
	 * @param orderBackfeeItemDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询退费订单详情表数据", notes = "分页查询退费订单详情表数据")
	public AjaxResult listForPage(OrderBackfeeItemDto orderBackfeeItemDto){
		DataGridView dataGridView = orderBackfeeItemService.listPage(orderBackfeeItemDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加退费订单详情表数据
	 * @param orderBackfeeItemDto
	 * @return
	 */
	@PostMapping("addOrderBackfeeItem")
	@ApiOperation(value = "添加退费订单详情表数据", notes = "添加退费订单详情表数据")
	public AjaxResult addOrderBackfeeItem(@CurrUser OrderBackfeeItemDto orderBackfeeItemDto){
		return AjaxResult.toAjax(orderBackfeeItemService.insert(orderBackfeeItemDto));
	}

	/**
	 * 更新退费订单详情表数据
	 * @param orderBackfeeItemDto
	 * @return
	 */
	@PutMapping("updateOrderBackfeeItem")
	@ApiOperation(value = "更新退费订单详情表数据", notes = "更新退费订单详情表数据")
	public AjaxResult updateOrderBackfeeItem(@CurrUser OrderBackfeeItemDto orderBackfeeItemDto){
		return AjaxResult.toAjax(orderBackfeeItemService.update(orderBackfeeItemDto));
	}

	/**
	 * 删除退费订单详情表数据(可批量删除)
	 * @param orderBackfeeItemIds
	 * @return
	 */
	@DeleteMapping("deleteOrderBackfeeItemByIds/{orderBackfeeItemIds}")
	@ApiOperation(value = "根据ID退费订单详情表数据", notes = "根据ID删除退费订单详情表数据")
	public AjaxResult deleteOrderBackfeeItemByIds(@PathVariable Long[] orderBackfeeItemIds){
		return AjaxResult.toAjax(orderBackfeeItemService.deleteByIds(orderBackfeeItemIds));
	}

	/**
	 * 根据ID查询退费订单详情表数据
	 * @param orderBackfeeItemId
	 * @return
	 */
	@GetMapping("getOrderBackfeeItemById/{orderBackfeeItemId}")
	@ApiOperation(value = "根据ID查询退费订单详情表数据", notes = "根据ID查询退费订单详情表数据")
	public AjaxResult getOne(@PathVariable String orderBackfeeItemId){
		return AjaxResult.success("查询成功", orderBackfeeItemService.findById(orderBackfeeItemId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllOrderBackfeeItem")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllOrderBackfeeItem() {
		return AjaxResult.success(orderBackfeeItemService.selectAll());
	}

}
