package com.twofish.service;

import com.twofish.base.BaseService;
import com.twofish.domain.Role;
import com.twofish.dto.RoleDto;

/**
 * 角色服务接口
 */
public interface RoleService extends BaseService<Role, RoleDto> {

    /**
     * 更改角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    int updateMenu(Long roleId, Long[] menuIds);

}
