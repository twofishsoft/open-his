package com.twofish.controller.system;

import com.twofish.annotation.CurrUser;
import com.twofish.domain.RoleUser;
import com.twofish.dto.RoleDto;
import com.twofish.service.RoleService;
import com.twofish.service.RoleUserService;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

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
    @Resource
    private RoleUserService roleUserService;

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
     * @return
     */
    @GetMapping("selectAllRole")
    @ApiOperation(value = "查询所有角色数据", notes = "查询所有角色数据")
    public AjaxResult getAllRole(){
        return AjaxResult.success(this.roleService.selectAll());
    }

    /**
     * 添加角色数据
     * @param roleDto
     * @return
     */
    @PostMapping("addRole")
    @ApiOperation(value = "添加角色数据", notes = "添加角色数据")
    public AjaxResult addDictData(@CurrUser RoleDto roleDto){
        return AjaxResult.toAjax(this.roleService.insert(roleDto));
    }

    /**
     * 根据ID查询角色数据
     * @param roleId
     * @return
     */
    @GetMapping("getRoleById/{roleId}")
    @ApiOperation(value = "根据ID查询角色数据", notes = "根据ID查询角色数据")
    public AjaxResult getOne(@PathVariable Long roleId){
        return AjaxResult.success("查询成功", this.roleService.getOneById(roleId));
    }

    /**
     * 更新角色数据
     * @param roleDto
     * @return
     */
    @PutMapping("updateRole")
    @ApiOperation(value = "更新角色数据", notes = "更新角色数据")
    public AjaxResult updateRole(@CurrUser(name = "updateBy") RoleDto roleDto){
        return AjaxResult.toAjax(this.roleService.update(roleDto));
    }

    /**
     * 删除角色数据(可批量删除)
     * @param roleIds 角色数据ID数组
     * @return
     */
    @DeleteMapping("deleteRoleByIds/{roleIds}")
    @ApiOperation(value = "根据ID删除角色数据", notes = "根据ID删除角色数据")
    public AjaxResult deleteRoleByIds(@PathVariable Long[] roleIds) {
        return AjaxResult.toAjax(this.roleService.deleteByIds(roleIds));
    }

    /**
     * 更新角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("saveRoleMenu/{roleId}/{menuIds}")
    @ApiOperation(value = "更新角色菜单关系", notes = "更新角色菜单关系")
    public AjaxResult saveRoleMenu(@PathVariable Long roleId, @PathVariable Long[] menuIds) {
        return AjaxResult.toAjax(this.roleService.updateMenu(roleId, menuIds));
    }

    /**
     * 获取用户所对应的角色
     * @param userId
     * @return
     */
    @GetMapping("getRoleIdsByUserId/{userId}")
    @ApiOperation(value = "获取用户所对应的角色", notes = "获取用户所对应的角色")
    public AjaxResult getUserRole(@PathVariable Long userId) {
        return AjaxResult.success(this.roleUserService.getOneByAttr(RoleUser.COL_USER_ID, userId));
    }

}
