package com.twofish.service;

import com.twofish.domain.Role;
import com.twofish.domain.RoleUser;
import com.twofish.dto.RoleDto;
import com.twofish.vo.DataGridView;

import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService {

    /**
     * 分页查询角色数据
     * @param roleDto
     * @return
     */
    DataGridView listPage(RoleDto roleDto);

    /**
     * 查询所有角色数据
     * @return
     */
    List<Role> queryAllRole();

    /**
     * 添加角色
     * @param roleDto
     * @return
     */
    int insertRole(RoleDto roleDto);

    /**
     * 修改角色
     * @param roleDto
     * @return
     */
    int updateRole(RoleDto roleDto);

    /**
     * 删除角色数据(可批量删除)
      * @param roleIds
     * @return
     */
    int deleteRoleByIds(Long[] roleIds);

    /**
     * 根据角色ID查询角色
     * @param roleId
     * @return
     */
    Role getOne(Long roleId);

    /**
     * 更改角色菜单关系
     * @param roleDto
     * @return
     */
    int updateMenu(RoleDto roleDto);

    /**
     * 获取用户所对应的角色
     * @param userId
     * @return
     */
    List<RoleUser> getUserRole(Long userId);
}
