package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.Role;
import com.twofish.domain.RoleMenu;
import com.twofish.dto.RoleDto;
import com.twofish.mapper.RoleMapper;
import com.twofish.mapper.RoleMenuMapper;
import com.twofish.service.DictDataService;
import com.twofish.service.RoleService;
import com.twofish.service.base.BaseServiceImpl;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色服务实现
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDto> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private DictDataService dictDataService;

    @Override
    public BaseMapper getMapper() {
        return roleMapper;
    }

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
        page.getRecords().stream().forEach(role -> {
            role.setStatusName(dictDataService.queryDataByTypeAndValue("sys_normal_disable", role.getStatus()));
        });
        return new DataGridView(page.getTotal(), page.getRecords());
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

}
