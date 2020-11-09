package com.twofish.service;

import com.twofish.domain.Menu;
import com.twofish.domain.SimpleUser;
import com.twofish.vo.TreeVo;

import java.util.List;

public interface MenuService {

    /**
     * 根据用户查询菜单返回
     * 如果是超级管理员，返回所有菜单权限
     * 如果不是超级管理员，那就得看用户id，根据id关联角色菜单
     * @param isAdmin 是否超级管理员
     * @param simpleUser 里面有用户id
     * @return
     */
    List<Menu> selectMenuTree(Boolean isAdmin, SimpleUser simpleUser);

    /**
     * 查询所有的菜单
     */
    List<TreeVo> queryAllMenu();

    /**
     * 查询角色选中的菜单
     */
    Long[] roleCheckedMenus(Long roleId);
}
