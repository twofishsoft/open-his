package com.twofish.service;

import com.twofish.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
public interface UserService {

    /**
     * 根据手机号查询用户
     * @param phone 用户手机号
     * @return 单个用户
     */
    User querybyphone(String phone);

    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 单个用户
     */
    User getOne(Long userId);
}
