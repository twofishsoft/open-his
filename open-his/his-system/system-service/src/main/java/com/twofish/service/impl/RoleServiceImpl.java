package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Role;
import com.twofish.domain.RoleMenu;
import com.twofish.dto.RoleDto;
import com.twofish.mapper.RoleMapper;
import com.twofish.mapper.RoleMenuMapper;
import com.twofish.service.DictDataService;
import com.twofish.service.RoleService;
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
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;
    @Resource
    private DictDataService dictDataService;

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

    /**
     * 查找所有有效数据
     * @return
     */
    public List<Role> selectAll() {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return roleMapper.selectList(qw);
    }

    /**
     * 添加数据
     * @param roleDto
     * @return
     */
    public int insert(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        return roleMapper.insert(role);
    }

    /**
     * 修改数据
     * @param roleDto
     * @return
     */
    public int update(RoleDto roleDto) {
        Role role = new Role();
        BeanUtil.copyProperties(roleDto, role);
        return roleMapper.updateById(role);
    }

    public int update(Role role) {
        return roleMapper.updateById(role);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return roleMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    public Role getOneById(Long id) {
        return roleMapper.selectById(id);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public List<Role> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return roleMapper.selectList(qw);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public Role queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<Role> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return roleMapper.selectOne(qw);
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
