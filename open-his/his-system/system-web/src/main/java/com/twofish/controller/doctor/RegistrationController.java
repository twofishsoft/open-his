package com.twofish.controller.doctor;

import com.twofish.annotation.CurrUser;
import com.twofish.dto.RegistrationDto;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import twofish.service.RegistrationService;
import javax.annotation.Resource;

/**
 * @author ww
 * @description 【挂号信息】控制器
 * @date 2020-11-17 15:03:48
 **/
@RestController
@Log4j2
@Api(value = "挂号信息数据接口",tags = "挂号信息数据接口")
@RequestMapping("/system/registration/")
public class RegistrationController {

	@Resource
	private RegistrationService registrationService;

	/**
	 * 分页查询用户数据
	 * @param registrationDto
	 * @return
	 */
	@GetMapping("listForPage")
	@ApiOperation(value = "分页查询用户数据", notes = "用户数据分页")
	public AjaxResult listForPage(RegistrationDto registrationDto){
		DataGridView dataGridView = registrationService.listPage(registrationDto);
		return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
	}

	/**
	 * 添加挂号信息数据
	 * @param registrationDto
	 * @return
	 */
	@PostMapping("addRegistration")
	@ApiOperation(value = "添加挂号信息数据", notes = "添加挂号信息数据")
	public AjaxResult addDictData(@CurrUser RegistrationDto registrationDto){
		return AjaxResult.toAjax(registrationService.insert(registrationDto));
	}

	/**
	 * 更新挂号信息数据
	 * @param registrationDto
	 * @return
	 */
	@PutMapping("updateRegistration")
	@ApiOperation(value = "更新挂号信息数据", notes = "更新挂号信息数据")
	public AjaxResult updateUser(@CurrUser RegistrationDto registrationDto){
		return AjaxResult.toAjax(registrationService.update(registrationDto));
	}

	/**
	 * 删除挂号信息数据(可批量删除)
	 * @param registrationIds
	 * @return
	 */
	@DeleteMapping("deleteRegistrationByIds/{registrationIds}")
	@ApiOperation(value = "根据ID挂号信息数据", notes = "根据ID删除挂号信息数据")
	public AjaxResult deleteUserByIds(@PathVariable Long[] registrationIds){
		return AjaxResult.toAjax(registrationService.deleteByIds(registrationIds));
	}

	/**
	 * 根据ID查询挂号信息数据
	 * @param registrationId
	 * @return
	 */
	@GetMapping("getRegistrationById/{registrationId}")
	@ApiOperation(value = "根据ID查询挂号信息数据", notes = "根据ID查询挂号信息数据")
	public AjaxResult getOne(@PathVariable Long registrationId){
		return AjaxResult.success("查询成功", registrationService.getOneById(registrationId));
	}

	/**
	 * 查询所有可用信息
	 * @return
	 */
	@GetMapping("selectAllRegistration")
	@ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
	public AjaxResult selectAllUser() {
		return AjaxResult.success(registrationService.selectAll());
	}

}
