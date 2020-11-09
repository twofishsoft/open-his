package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Dept;
import com.twofish.domain.Role;
import com.twofish.domain.RoleMenu;
import com.twofish.domain.RoleUser;
import com.twofish.dto.RoleDto;
import com.twofish.mapper.RoleMapper;
import com.twofish.mapper.RoleMenuMapper;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.service.RoleService;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色服务实现
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public DataGridView listPage(RoleDto RoleDto) {
        Page<Role> page = new Page<>(RoleDto.getPageNum(), RoleDto.getPageSize());
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(RoleDto.getRoleName()), Role.COL_ROLE_Name, RoleDto.getRoleName());
        qw.eq(StringUtils.isNotBlank(RoleDto.getRoleCode()), Role.COL_ROLE_CODE, RoleDto.getRoleCode());
        qw.eq(StringUtils.isNotBlank(RoleDto.getStatus()), Role.COL_STATUS, RoleDto.getStatus());
        qw.ge(null != RoleDto.getBeginTime(), Role.COL_CREATE_TIME, RoleDto.getBeginTime());
        qw.le(null != RoleDto.getEndTime(), Role.COL_CREATE_TIME, RoleDto.getEndTime());
        roleMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Role> queryAllRole() {
        QueryWrapper<Role> qw = new QueryWrapper();
        qw.eq(Dept.COL_STATUS, Constants.STATUS_TRUE);
        return roleMapper.selectList(qw);
    }

    @Override
    public int insertRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        //设置创建人
        role.setCreateBy(roleDto.getSimpleUser().getUserName());
        role.setCreateTime(new Date());
        return roleMapper.insert(role);
    }

    @Override
    public int updateRole(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        //设置创建人
        role.setUpdateBy(roleDto.getSimpleUser().getUserName());
        return roleMapper.updateById(role);
    }

    @Override
    public int deleteRoleByIds(Long[] roleIds) {
        List<Long> ids = Arrays.asList(roleIds);
        if(ids != null && ids.size() > 0){
            return roleMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public Role getOne(Long roleId) {
        return roleMapper.selectById(roleId);
    }

    @Override
    public int updateMenu(Long roleId, Long[] menuIds) {
        if (null != roleId) {
            List<RoleMenu> list = new ArrayList();
            roleMenuMapper.deleteRoleMenuByRoleId(roleId);
            if (null != menuIds && menuIds.length != 0) {
                Arrays.stream(menuIds).forEach(menuId -> {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menuId);
                    roleMenu.setRoleId(roleId);
                    list.add(roleMenu);
                });
            }
            if (list.size() > 0) {
                roleMenuMapper.batchRoleMenu(list);
            }
        }
        return 1;
    }

    @Override
    public List<RoleUser> getUserRole(Long userId) {
        QueryWrapper<RoleUser> qw = new QueryWrapper<>();
        qw.eq(RoleUser.COL_USER_ID, userId);
        return roleUserMapper.selectList(qw);
    }

    @Override
    public Role getRole(String roleCode) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(Role.COL_ROLE_CODE, roleCode);
        return roleMapper.selectOne(qw);
    }

}
