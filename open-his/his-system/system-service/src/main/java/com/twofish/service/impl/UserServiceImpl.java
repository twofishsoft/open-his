package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.RoleUser;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.mapper.UserMapper;
import com.twofish.service.UserService;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Ytyy
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public DataGridView listPage(UserDto userDto) {
        Page<User> page = new Page<>(userDto.getPageNum(), userDto.getPageSize());
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(null != userDto.getDeptId(), User.COL_DEPT_ID, userDto.getDeptId());
        qw.like(StringUtils.isNotBlank(userDto.getUserName()), User.COL_USER_NAME, userDto.getUserName());
        qw.eq(StringUtils.isNotBlank(userDto.getPhone()), User.COL_PHONE, userDto.getPhone());
        qw.eq(StringUtils.isNotBlank(userDto.getStatus()), User.COL_STATUS, userDto.getStatus());
        qw.ge(null != userDto.getBeginTime(), User.COL_CREATE_TIME, userDto.getBeginTime());
        qw.le(null != userDto.getEndTime(), User.COL_CREATE_TIME, userDto.getEndTime());
        userMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public int insertUser(UserDto userDto) {
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        //设置创建人
        user.setCreateBy(userDto.getSimpleUser().getUserName());
        user.setCreateTime(new Date());
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(UserDto userDto) {
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        //设置创建人
        user.setUpdateBy(userDto.getSimpleUser().getUserName());
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUserByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return userMapper.deleteBatchIds(ids);
        }
        return -1;
    }

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
    public int resetPwd(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int updateRole(UserDto userDto) {
        Long[] userIds = userDto.getUserIds();
        if (null != userIds && userIds.length != 0) {
            for (Long userId : userIds) {
                List<RoleUser> list = new ArrayList();
                roleUserMapper.deleteRoleUserByUserId(userId);
                Long[] roleIds = userDto.getRoleIds();
                if (null != roleIds && roleIds.length != 0) {
                    for (Long roleId : roleIds) {
                        RoleUser ru = new RoleUser();
                        ru.setUserId(userId);
                        ru.setRoleId(roleId);
                        list.add(ru);
                    }
                }
                if (list.size() > 0) {
                    roleUserMapper.batchUserRole(list);
                }
            }
        }
        return 1;
    }


}
