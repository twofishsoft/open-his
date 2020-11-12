package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Role;
import com.twofish.domain.RoleUser;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.mapper.UserMapper;
import com.twofish.service.RoleService;
import com.twofish.service.UserService;
import com.twofish.service.base.BaseService;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ytyy
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleUserMapper roleUserMapper;
    @Resource
    private RoleService roleService;

    @Override
    public BaseMapper getMapper() {
        return userMapper;
    }

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
    public List<User> getUsersNeedScheduling(Long deptId) {
        Role role = roleService.getRole(Constants.DOCTOR_CODE);
        if (null != role) {
            return userMapper.getUsersNeedScheduling(
                    new User(Constants.STATUS_TRUE, Constants.SCHEDULING_FLAG_TRUE, deptId, role.getRoleId())
            );
        }
        return null;
    }

    @Override
    public User querybyphone(String phone) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(User.COL_PHONE, phone);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public int resetPwd(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int updateRole(UserDto userDto) {
        Long userId = userDto.getUserId();
        int i = -1;
        if (null != userId) {
            List<RoleUser> list = new ArrayList();
            roleUserMapper.deleteRoleUserByUserId(userId);
            Long[] roleIds = userDto.getRoleIds();
            if (null != roleIds && roleIds.length != 0) {
                Arrays.stream(roleIds).forEach(roleId -> {
                    RoleUser ru = new RoleUser();
                    ru.setUserId(userId);
                    ru.setRoleId(roleId);
                    list.add(ru);
                } );
            }
            if (list.size() > 0) {
                i = roleUserMapper.batchUserRole(list);
            }
        }
        return i;
    }


}
