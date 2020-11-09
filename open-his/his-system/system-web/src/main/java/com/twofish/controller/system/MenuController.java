package com.twofish.controller.system;

import com.twofish.domain.Menu;
import com.twofish.domain.Role;
import com.twofish.dto.RoleDto;
import com.twofish.service.DictDataService;
import com.twofish.service.MenuService;
import com.twofish.service.RoleService;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import com.twofish.vo.TreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 菜单数据接口
 * @author ww
 */
@RestController
@Log4j2
@Api(value = "菜单数据接口",tags = "菜单数据接口")
@RequestMapping("/system/menu/")
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取所有的菜单 和 角色选中的菜单
     * @return
     */
    @GetMapping("getAllMenu/{roleId}")
    @ApiOperation(value = "获取所有的菜单", notes = "获取所有的菜单")
    public AjaxResult getAllMenu(@PathVariable Long roleId) {
        AjaxResult success = AjaxResult.success();
        success.put("data", this.menuService.queryAllMenu());
        success.put("checkedMenus", this.menuService.roleCheckedMenus(roleId));
        return success;
    }

    /**
     * 根据角色ID查询已分配菜单ID[只查子节点]
     * @param roleId
     * @return
     */
    @GetMapping("getMenuIdsByRoleId/{roleId}")
    @ApiOperation(value = "根据角色ID查询已分配菜单ID", notes = "根据角色ID查询已分配菜单ID")
    public AjaxResult getMenuIdsByRoleId(@PathVariable Long roleId) {
        return AjaxResult.success(this.menuService.roleCheckedMenus(roleId));
    }

}
