package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Dept;
import com.twofish.domain.Role;
import com.twofish.domain.RoleUser;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.mapper.UserMapper;
import com.twofish.service.DeptService;
import com.twofish.service.DictDataService;
import com.twofish.service.RoleService;
import com.twofish.service.UserService;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleUserMapper roleUserMapper;
    @Resource
    private RoleService roleService;
    @Resource
    private DictDataService dictDataService;
    @Resource
    private DeptService deptService;

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
        page.getRecords().stream().forEach(user -> {
            user.setStatusName(dictDataService.queryDataByTypeAndValue("sys_normal_disable", user.getStatus()));
            user.setBackgroundName(dictDataService.queryDataByTypeAndValue("sys_user_background", user.getBackground()));
            user.setLevelName(dictDataService.queryDataByTypeAndValue("sys_user_level", user.getUserRank()));
            user.setSexName(dictDataService.queryDataByTypeAndValue("sys_user_sex", user.getSex()));
            user.setSchedulingFlagName(dictDataService.queryDataByTypeAndValue("his_scheduling_flag", user.getSchedulingFlag()));
            Dept dept = deptService.getOneById(user.getDeptId());
            if (null != dept) {
                user.setDept(dept);
            }
        });
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 查找所有有效数据
     * @return
     */
    public List<User> selectAll() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return userMapper.selectList(qw);
    }

    /**
     * 添加数据
     * @param userDto
     * @return
     */
    public int insert(UserDto userDto) {
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        return userMapper.insert(user);
    }

    /**
     * 修改数据
     * @return
     */
    public int update(UserDto userDto) {
        User user = new User();
        BeanUtil.copyProperties(userDto, user);
        return userMapper.updateById(user);
    }

    public int update(User user) {
        return userMapper.updateById(user);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return userMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    public User getOneById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public List<User> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return userMapper.selectList(qw);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public User queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return userMapper.selectOne(qw);
    }

    @Override
    public User queryByPhone(String phone) {
        return this.queryOneByAttr(User.COL_PHONE, phone);
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

    @Override
    public List<User> getUsersNeedScheduling(Long deptId) {
        Role role = roleService.queryOneByAttr(Role.COL_ROLE_CODE, Constants.DOCTOR_CODE);
        if (null != role) {
            return userMapper.getUsersNeedScheduling(
                    new UserDto(Constants.STATUS_TRUE, Constants.SCHEDULING_FLAG_TRUE, deptId, role.getRoleId())
            );
        }
        return new ArrayList<>();
    }
}
