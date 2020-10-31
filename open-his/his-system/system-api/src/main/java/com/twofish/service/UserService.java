package com.twofish.service;

import com.twofish.domain.User;

/**
 * @author www32
 * @date 2020/10/31
 * @project open-his
 * @description 用户业务接口
 * @Version 1.0
 */
public interface UserService {

    /**
     * 根据手机号查询用户
     * @param phone
     * @return
     */
    User queryByPhone(String phone);

    /**
     * 根据用户id查询用户
     * @param userId
     * @return
     */
    User getOne(Long userId);
}
