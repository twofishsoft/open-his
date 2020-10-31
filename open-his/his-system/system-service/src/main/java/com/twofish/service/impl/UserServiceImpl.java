package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.domain.User;
import com.twofish.mapper.UserMapper;
import com.twofish.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author www32
 * @date 2020/10/31
 * @project open-his
 * @description 用户业务接口实现类
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryByPhone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.COL_PHONE, phone);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getOne(Long userId) {
        return userMapper.selectById(userId);
    }
}
