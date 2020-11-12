package com.twofish.service;

import com.twofish.base.BaseService;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.vo.DataGridView;
import java.util.List;

/**
 * @author Ytyy
 * @version 1.0
 */
public interface UserService extends BaseService<User> {

    /**
     * 分页查询用户数据
     * @param userDto
     * @return
     */
    DataGridView listPage(UserDto userDto);

    /**
     * 查询需要排班的医生信息
     * @return
     */
    List<User> getUsersNeedScheduling(Long deptId);

    /**
     * 根据手机号查询用户
     *
     * @param phone 用户手机号
     * @return 单个用户实体
     */
    User querybyphone(String phone);

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
