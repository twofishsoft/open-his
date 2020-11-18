package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.domain.CareOrderItem;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.dto.CheckResultDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.web.bind.annotation.*;
import twofish.service.CareOrderItemService;
import twofish.service.CheckResultService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【检查结果】控制器
 * @date 2020-11-17 16:35:08
 **/
@RestController
@Log4j2
@Api(value = "检查结果数据接口",tags = "检查结果数据接口")
@RequestMapping("/doctor/check/")
public class CheckResultController {

	@Resource
	private CheckResultService checkResultService;
	@Resource
	private CareOrderItemService careOrderItemService;

	/**
	 * 查询所有检查中的和检查完成了的项目
	 * @param checkResultDto
	 * @return
	 */
	@GetMapping("queryAllCheckResultForPage")
	@ApiOperation(value = "查询所有检查中的和检查完成了的项目", notes = "查询所有检查中的和检查完成了的项目")
	public AjaxResult queryAllCheckResultForPage(CheckResultDto checkResultDto){
		DataGridView dataGridView = checkResultService.listPage(checkResultDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 查询所有检查中的项目
	 * @param checkResultDto
	 * @return
	 */
	@GetMapping("queryAllCheckingResultForPage")
	@ApiOperation(value = "查询所有检查中的项目", notes = "查询所有检查中的项目")
	public AjaxResult queryAllCheckingResultForPage(CheckResultDto checkResultDto){
		DataGridView dataGridView = checkResultService.queryAllCheckingResultForPage(checkResultDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加检查结果数据
	 * @param checkResultDto
	 * @return
	 */
	@PostMapping("addCheckResult")
	@ApiOperation(value = "添加检查结果数据", notes = "添加检查结果数据")
	public AjaxResult addCheckResult(@CurrUser CheckResultDto checkResultDto){
		return AjaxResult.toAjax(checkResultService.insert(checkResultDto));
	}

	/**
	 * 更新检查结果数据
	 * @param checkResultDto
	 * @return
	 */
	@PutMapping("updateCheckResult")
	@ApiOperation(value = "更新检查结果数据", notes = "更新检查结果数据")
	public AjaxResult updateCheckResult(@CurrUser CheckResultDto checkResultDto){
		return AjaxResult.toAjax(checkResultService.update(checkResultDto));
	}

	/**
	 * 删除检查结果数据(可批量删除)
	 * @param checkResultIds
	 * @return
	 */
	@DeleteMapping("deleteCheckResultByIds/{checkResultIds}")
	@ApiOperation(value = "根据ID检查结果数据", notes = "根据ID删除检查结果数据")
	public AjaxResult deleteCheckResultByIds(@PathVariable Long[] checkResultIds){
		return AjaxResult.toAjax(checkResultService.deleteByIds(checkResultIds));
	}

	/**
	 * 根据ID查询检查结果数据
	 * @param checkResultId
	 * @return
	 */
	@GetMapping("getCheckResultById/{checkResultId}")
	@ApiOperation(value = "根据ID查询检查结果数据", notes = "根据ID查询检查结果数据")
	public AjaxResult getOne(@PathVariable String checkResultId){
		return AjaxResult.success("查询成功", checkResultService.findById(checkResultId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllCheckResult")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllCheckResult() {
		return AjaxResult.success(checkResultService.selectAll());
	}

	/**
	 * 根据挂号单号和项目IDS查询要检查的项目
	 * @param careOrderItemDto
	 * @return
	 */
	@GetMapping("queryNeedCheckItem")
	@ApiOperation(value = "根据挂号单号和项目IDS查询要检查的项目", notes = "根据挂号单号和项目IDS查询要检查的项目")
	public AjaxResult queryNeedCheckItem(CareOrderItemDto careOrderItemDto) {
		return AjaxResult.success(careOrderItemService.queryNeedCheckItem(careOrderItemDto));
	}

	/**
	 * 完成检查
	 * @param checkResultDto
	 * @return
	 */
	@PostMapping("completeCheckResult")
	@ApiOperation(value = "完成检查", notes = "完成检查")
	public AjaxResult completeCheckResult(@CurrUser CheckResultDto checkResultDto) {
		return AjaxResult.toAjax(checkResultService.completeCheckResult(checkResultDto));
	}

	/**
	 * 开始检查
	 * @param itemId
	 * @return
	 */
	@PostMapping("startCheck/{itemId}")
	@ApiOperation(value = "开始检查", notes = "开始检查")
	public AjaxResult startCheck(@PathVariable String itemId) {
		return AjaxResult.toAjax(checkResultService.startCheck(itemId));
	}

	/**
	 * 根据检查单号查询要检查的项目详情
	 * @param itemId
	 * @return
	 */
	@GetMapping("queryCheckItemByItemId/{itemId}")
	@ApiOperation(value = "根据检查单号查询要检查的项目详情", notes = "根据检查单号查询要检查的项目详情")
	public AjaxResult queryCheckItemByItemId(@PathVariable String itemId) {
		return AjaxResult.success(checkResultService.queryCheckItemByItemId(itemId));
	}

}
