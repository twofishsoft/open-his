package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.User;
import com.twofish.mapper.UserMapper;
import com.twofish.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Ytyy
 * @version 1.0
 */
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
    public User getOne(Long userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public Page<User> getUserByPage(Page<User> page, Wrapper<User> wrapper) {
        return userMapper.getUserAndDeptNamePage(page, wrapper);
    }

    @Override
    public Integer addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer deleteUserById(Long userId) {
        return userMapper.deleteById(userId);
    }
}
