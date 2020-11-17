package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.RegisteredItemDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.RegisteredItemService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【挂号项目】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "挂号项目数据接口",tags = "挂号项目数据接口")
@RequestMapping("/system/registeredItem/")
public class RegisteredItemController {

	@Resource
	private RegisteredItemService registeredItemService;

	/**
	 * 分页查询挂号项目
	 * @param registeredItemDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询挂号项目数据", notes = "分页查询挂号项目数据")
	public AjaxResult listForPage(RegisteredItemDto registeredItemDto){
		DataGridView dataGridView = registeredItemService.listPage(registeredItemDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加挂号项目数据
	 * @param registeredItemDto
	 * @return
	 */
	@PostMapping("addRegisteredItem")
	@ApiOperation(value = "添加挂号项目数据", notes = "添加挂号项目数据")
	public AjaxResult addDictData(@CurrUser RegisteredItemDto registeredItemDto){
		return AjaxResult.toAjax(registeredItemService.insert(registeredItemDto));
	}

	/**
	 * 更新挂号项目数据
	 * @param registeredItemDto
	 * @return
	 */
	@PutMapping("updateRegisteredItem")
	@ApiOperation(value = "更新挂号项目数据", notes = "更新挂号项目数据")
	public AjaxResult updateUser(@CurrUser RegisteredItemDto registeredItemDto){
		return AjaxResult.toAjax(registeredItemService.update(registeredItemDto));
	}

	/**
	 * 删除挂号项目数据(可批量删除)
	 * @param registeredItemIds
	 * @return
	 */
	@DeleteMapping("deleteRegisteredItemByIds/{registeredItemIds}")
	@ApiOperation(value = "根据ID挂号项目数据", notes = "根据ID删除挂号项目数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] registeredItemIds){
		return AjaxResult.toAjax(registeredItemService.deleteByIds(registeredItemIds));
	}

	/**
	 * 根据ID查询挂号项目数据
	 * @param {registeredItemId
	 * @return
	 */
	@GetMapping("getRegisteredItemById/{registeredItemId}")
	@ApiOperation(value = "根据ID查询挂号项目数据", notes = "根据ID查询挂号项目数据")
	public AjaxResult getOne(@PathVariable Long registeredItemId){
		return AjaxResult.success("查询成功", registeredItemService.getOneById(registeredItemId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllRegisteredItem")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllRegisteredItem() {
		return AjaxResult.success(registeredItemService.selectAll());
	}

}
