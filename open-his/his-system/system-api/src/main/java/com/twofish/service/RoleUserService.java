package com.twofish.service;

import com.twofish.domain.RoleUser;
import com.twofish.dto.RoleUserDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * 用户角色服务接口
 */
public interface RoleUserService {

    /**
     * 查询所有可用信息
     * @return
     */
    List<RoleUser> selectAll();

    /**
     * 添加
     * @param roleUserDto
     * @return
     */
    int insert(RoleUserDto roleUserDto);

    /**
     * 修改
     * @param roleUserDto
     * @return
     */
    int update(RoleUserDto roleUserDto);

    /**
     * 根据ID修改
     * @param roleUser
     * @return
     */
    int update(RoleUser roleUser);

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
    RoleUser getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<RoleUser> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    RoleUser queryOneByAttr(String attr, Object attrValue);
}
