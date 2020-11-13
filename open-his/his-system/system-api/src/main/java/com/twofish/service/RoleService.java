package com.twofish.service;

import com.twofish.base.BaseService;
import com.twofish.domain.Role;
import com.twofish.dto.RoleDto;
import com.twofish.vo.DataGridView;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 分页查询角色数据
     * @param roleDto
     * @return
     */
    DataGridView listPage(RoleDto roleDto);

    /**
     * 更改角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    int updateMenu(Long roleId, Long[] menuIds);

}
