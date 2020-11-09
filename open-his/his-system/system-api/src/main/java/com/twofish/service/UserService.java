package com.twofish.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.User;

import java.util.List;

/**
 * @author Ytyy
 * @version 1.0
 */
public interface UserService {

    /**
     * 根据手机号查询用户
     *
     * @param phone 用户手机号
     * @return 单个用户实体
     */
    User querybyphone(String phone);

    /**
     * 根据用户id查询用户
     *
     * @param userId 用户id
     * @return 单个用户实体
     */
    User getOne(Long userId);

    /**
     * 分页查询用户
     *
     * @param page 分页实体类
     * @param wrapper 条件查询
     * @return 用户列表
     */
    Page<User> getUserByPage(Page<User> page, Wrapper<User> wrapper);

    /**
     * 添加用户
     *
     * @param user 用户实体
     * @return 受影响的行数
     */
    Integer addUser(User user);

    /**
     * 根据id删除用户
     *
     * @param userId 用户id
     * @return 受影响的行数
     */
    Integer deleteUserById(Long userId);
}
