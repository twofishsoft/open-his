package com.twofish.service;

import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.vo.DataGridView;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author ww
 */
public interface UserService {

    /**
     * 分页查询用户数据
     * @param userDto
     * @return
     */
    DataGridView listPage(UserDto userDto);

    /**
     * 查询所有可用信息
     * @return
     */
    List<User> selectAll();

    /**
     * 添加
     * @param userDto
     * @return
     */
    int insert(UserDto userDto);

    /**
     * 修改
     * @param userDto
     * @return
     */
    int update(UserDto userDto);

    /**
     * 根据ID修改
     * @param t
     * @return
     */
    int update(User t);

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
    User getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<User> queryByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    User queryOneByAttr(String attr, Object attrValue);

    /**
     * 根据手机号查询用户
     * @param phone 用户手机号
     * @return
     */
    User queryByPhone(String phone);

    /**
     * 更改用户角色
     * @param userDto
     * @return
     */
    int updateRole(UserDto userDto);

    /**
     * 查询需要排班的医生信息
     * @param deptId
     * @return
     */
    List<User> getUsersNeedScheduling(Long deptId);

}
