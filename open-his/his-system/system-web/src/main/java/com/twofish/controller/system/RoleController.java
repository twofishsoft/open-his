package com.twofish.controller.system;

import com.twofish.dto.RoleDto;
import com.twofish.service.RoleService;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 角色数据接口
 * @author ww
 */
@RestController
@Log4j2
@Api(value = "角色数据接口",tags = "角色数据接口")
@RequestMapping("/system/role/")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 分页查询角色数据
     * @param roleDto
     * @return
     */
    @GetMapping("listForPage")
    @ApiOperation(value = "分页查询角色数据", notes = "角色数据分页")
    public AjaxResult listForPage(RoleDto roleDto){
        DataGridView dataGridView = this.roleService.listPage(roleDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 查询所有角色数据
     * @param roleDto
     * @return
     */
    @GetMapping("allRole")
    @ApiOperation(value = "查询所有角色数据", notes = "查询所有角色数据")
    public AjaxResult getAllRole(RoleDto roleDto){
        return AjaxResult.success(this.roleService.queryAllRole());
    }

    /**
     * 添加角色数据
     * @param roleDto
     * @return
     */
    @PostMapping("addRole")
    @ApiOperation(value = "添加角色数据", notes = "添加角色数据")
    public AjaxResult addDictData(@Validated RoleDto roleDto){
        //设置添加人
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.insertRole(roleDto));
    }

    /**
     * 根据ID查询角色数据
     * @param roleId
     * @return
     */
    @GetMapping("getOne/{roleId}")
    @ApiOperation(value = "根据ID查询角色数据", notes = "根据ID查询角色数据")
    public AjaxResult getOne(@PathVariable @Validated @NotNull(message = "角色ID不能为空") Long roleId){
        return AjaxResult.success("查询成功", this.roleService.getOne(roleId));
    }

    /**
     * 更新角色数据
     * @param roleDto
     * @return
     */
    @PutMapping("updateRole")
    @ApiOperation(value = "更新角色数据", notes = "更新角色数据")
    public AjaxResult updateRole(@Validated RoleDto roleDto){
        //设置修改人
        roleDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.roleService.updateRole(roleDto));
    }

    /**
     * 删除角色数据(可批量删除)
     * @param roleIds 角色数据ID数组
     * @return
     */
    @DeleteMapping("deleteRoleByIds/{roleIds}")
    @ApiOperation(value = "根据ID删除角色数据", notes = "根据ID删除角色数据")
    public AjaxResult deleteRoleByIds(@PathVariable @Validated @NotEmpty(message = "角色ID不能为空") Long[] roleIds) {
        return AjaxResult.toAjax(this.roleService.deleteRoleByIds(roleIds));
    }

    /**
     * 更新角色菜单关系
     * @param roleDto
     * @return
     */
    @PutMapping("updateMenu")
    @ApiOperation(value = "更新角色菜单关系", notes = "更新角色菜单关系")
    public AjaxResult updateMenu(@Validated RoleDto roleDto) {
        return AjaxResult.toAjax(this.roleService.updateMenu(roleDto));
    }

    /**
     * 获取用户所对应的角色
     * @param userId
     * @return
     */
    @GetMapping("getUserRole/{userId}")
    @ApiOperation(value = "获取用户所对应的角色", notes = "获取用户所对应的角色")
    public AjaxResult getUserRole(@PathVariable @Validated @NotEmpty(message = "用户ID不能为空") Long userId) {
        return AjaxResult.success(this.roleService.getUserRole(userId));
    }

}
