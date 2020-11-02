package com.twofish.service;

import com.twofish.domain.User;

public interface UserService {

    /**
     * 根据手机号查询用户
     *
     * @param phone
     * @return
     */
    User querybyphone(String phone);

    /**
     * 根据用户id查询用户
     *
     * @param userId
     * @return
     */
    User getone(Long userId);
}
