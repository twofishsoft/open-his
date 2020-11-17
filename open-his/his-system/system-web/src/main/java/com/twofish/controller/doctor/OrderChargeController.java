package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.OrderChargeDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/system/orderCharge/")
public class OrderChargeController {

	@Resource
	private OrderChargeService orderChargeService;

	/**
	 * 分页查询收费表
	 * @param orderChargeDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询收费表数据", notes = "分页查询收费表数据")
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
	public AjaxResult addDictData(@CurrUser OrderChargeDto orderChargeDto){
		return AjaxResult.toAjax(orderChargeService.insert(orderChargeDto));
	}

	/**
	 * 更新收费表数据
	 * @param orderChargeDto
	 * @return
	 */
	@PutMapping("updateOrderCharge")
	@ApiOperation(value = "更新收费表数据", notes = "更新收费表数据")
	public AjaxResult updateUser(@CurrUser OrderChargeDto orderChargeDto){
		return AjaxResult.toAjax(orderChargeService.update(orderChargeDto));
	}

	/**
	 * 删除收费表数据(可批量删除)
	 * @param orderChargeIds
	 * @return
	 */
	@DeleteMapping("deleteOrderChargeByIds/{orderChargeIds}")
	@ApiOperation(value = "根据ID收费表数据", notes = "根据ID删除收费表数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] orderChargeIds){
		return AjaxResult.toAjax(orderChargeService.deleteByIds(orderChargeIds));
	}

	/**
	 * 根据ID查询收费表数据
	 * @param {orderChargeId
	 * @return
	 */
	@GetMapping("getOrderChargeById/{orderChargeId}")
	@ApiOperation(value = "根据ID查询收费表数据", notes = "根据ID查询收费表数据")
	public AjaxResult getOne(@PathVariable Long orderChargeId){
		return AjaxResult.success("查询成功", orderChargeService.getOneById(orderChargeId));
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

}
