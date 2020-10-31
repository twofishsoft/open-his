package com.twofish.service;

import com.twofish.domain.Menu;
import com.twofish.domain.SimpleUser;

import java.util.List;

/**
 * @author www32
 * @date 2020/10/31
 * @project open-his
 * @description 菜单业务接口
 * @Version 1.0
 */
public interface MenuService {

    /**
     * 根据用户查询菜单返回
     * 如果是超级管理员，返回所有菜单权限
     * 如果不是超级管理员，那就得看用户id，根据id关联角色菜单
     *
     * @param isAdmin    是否超级管理员
     * @param simpleUser 里面有用户id
     * @return
     */
    public List<Menu> selectMenuTree(Boolean isAdmin, SimpleUser simpleUser);

}
