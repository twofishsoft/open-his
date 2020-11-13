package com.twofish.service;

import com.twofish.base.BaseService;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import java.util.List;

/**
 * @author ww
 */
public interface UserService extends BaseService<User, UserDto> {

    /**
     * 查询需要排班的医生信息
     * @param deptId
     * @return
     */
    List<User> getUsersNeedScheduling(Long deptId);

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

}
