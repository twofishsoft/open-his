package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.CheckResultDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/system/checkResult/")
public class CheckResultController {

	@Resource
	private CheckResultService checkResultService;

	/**
	 * 分页查询检查结果
	 * @param checkResultDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询检查结果数据", notes = "分页查询检查结果数据")
	public AjaxResult listForPage(CheckResultDto checkResultDto){
		DataGridView dataGridView = checkResultService.listPage(checkResultDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加检查结果数据
	 * @param checkResultDto
	 * @return
	 */
	@PostMapping("addCheckResult")
	@ApiOperation(value = "添加检查结果数据", notes = "添加检查结果数据")
	public AjaxResult addDictData(@CurrUser CheckResultDto checkResultDto){
		return AjaxResult.toAjax(checkResultService.insert(checkResultDto));
	}

	/**
	 * 更新检查结果数据
	 * @param checkResultDto
	 * @return
	 */
	@PutMapping("updateCheckResult")
	@ApiOperation(value = "更新检查结果数据", notes = "更新检查结果数据")
	public AjaxResult updateUser(@CurrUser CheckResultDto checkResultDto){
		return AjaxResult.toAjax(checkResultService.update(checkResultDto));
	}

	/**
	 * 删除检查结果数据(可批量删除)
	 * @param checkResultIds
	 * @return
	 */
	@DeleteMapping("deleteCheckResultByIds/{checkResultIds}")
	@ApiOperation(value = "根据ID检查结果数据", notes = "根据ID删除检查结果数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] checkResultIds){
		return AjaxResult.toAjax(checkResultService.deleteByIds(checkResultIds));
	}

	/**
	 * 根据ID查询检查结果数据
	 * @param {checkResultId
	 * @return
	 */
	@GetMapping("getCheckResultById/{checkResultId}")
	@ApiOperation(value = "根据ID查询检查结果数据", notes = "根据ID查询检查结果数据")
	public AjaxResult getOne(@PathVariable Long checkResultId){
		return AjaxResult.success("查询成功", checkResultService.getOneById(checkResultId));
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

}
