package com.twofish.service;

import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.vo.DataGridView;
import org.springframework.validation.annotation.Validated;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 分页查询用户数据
     * @param userDto
     * @return
     */
    DataGridView listPage(UserDto userDto);

    /**
     * 添加用户
     * @param userDto
     * @return
     */
    int insertUser(UserDto userDto);

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    int updateUser(UserDto userDto);

    /**
     * 删除用户数据(可批量删除)
      * @param userIds
     * @return
     */
    int deleteUserByIds(Long[] userIds);

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User querybyphone(String phone);

    /**
     * 根据用户ID查询用户
     * @param userId
     * @return
     */
    User getOne(Long userId);

    /**
     * 重置密码
     * @param user
     * @return
     */
    int resetPwd(User user);

    /**
     * 更改用户角色
     */
    int updateRole(UserDto userDto);
}
