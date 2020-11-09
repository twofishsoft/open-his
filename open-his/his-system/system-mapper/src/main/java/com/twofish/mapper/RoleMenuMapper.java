package com.twofish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.RoleMenu;

import java.util.List;

public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    int deleteRoleMenuByRoleId(Long roleId);

    int batchRoleMenu(List<RoleMenu> roleUserList);

    List<RoleMenu> roleCheckedMenus(Long roleId);
}