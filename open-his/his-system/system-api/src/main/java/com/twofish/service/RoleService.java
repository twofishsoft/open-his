package com.twofish.service;

import com.twofish.domain.Role;
import com.twofish.dto.RoleDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * 角色服务接口
 */
public interface RoleService {

    /**
     * 更改角色菜单关系
     * @param roleId
     * @param menuIds
     * @return
     */
    int updateMenu(Long roleId, Long[] menuIds);

    /**
     * 分页查询用户数据
     * @param roleDto
     * @return
     */
    DataGridView listPage(RoleDto roleDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<Role> selectAll();

    /**
     * 添加
     * @param roleDto
     * @return
     */
    int insert(RoleDto roleDto);

    /**
     * 修改
     * @param roleDto
     * @return
     */
    int update(RoleDto roleDto);

    /**
     * 根据ID修改
     * @param role
     * @return
     */
    int update(Role role);

    /**
     * 删除数据(可批量删除)
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    Role getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<Role> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    Role queryOneByAttr(String attr, Object attrValue);
}
