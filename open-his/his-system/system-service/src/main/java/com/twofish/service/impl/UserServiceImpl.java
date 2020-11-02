package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.domain.User;
import com.twofish.mapper.UserMapper;
import com.twofish.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User querybyphone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.COL_PHONE, phone);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getone(Long userId) {
        return userMapper.selectById(userId);
    }
}
