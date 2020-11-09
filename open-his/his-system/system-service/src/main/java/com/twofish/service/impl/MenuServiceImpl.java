package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.constants.Constants;
import com.twofish.domain.Menu;
import com.twofish.domain.RoleMenu;
import com.twofish.domain.SimpleUser;
import com.twofish.mapper.MenuMapper;
import com.twofish.mapper.RoleMenuMapper;
import com.twofish.service.MenuService;
import com.twofish.vo.TreeVo;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<Menu> selectMenuTree(Boolean isAdmin, SimpleUser simpleUser) {
        //菜单类型必须是目录或菜单 菜单状态必须是可用
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_M, Constants.MENU_TYPE_C);
        wrapper.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
       if(isAdmin){
           //是超级管理员，返回所有菜单
           return this.menuMapper.selectList(wrapper);
       }else {
           //系统用户，根据用户id关联角色菜单来查菜单[未完成]
           return this.menuMapper.selectList(wrapper);
       }
    }

    @Override
    public List<TreeVo> queryAllMenu() {
        //菜单类型必须是目录或菜单 菜单状态必须是可用
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.in(Menu.COL_MENU_TYPE, Constants.MENU_TYPE_M, Constants.MENU_TYPE_C);
        wrapper.eq(Menu.COL_STATUS, Constants.STATUS_TRUE);
        List<Menu> menus = this.menuMapper.selectList(wrapper);
        List<TreeVo> treeList = new ArrayList<>();
        menus.stream().forEach(menu -> {
            if (menu.getParentId() == 0) {
                TreeVo treeVo = new TreeVo(menu.getMenuId(), menu.getMenuName());
                getChildren(menus, treeVo);
                treeList.add(treeVo);
            }
        });
        return treeList;
    }

    @Override
    public Long[] roleCheckedMenus(Long roleId) {
        if (-1 == roleId) {
            return new Long[0];
        }
        List<RoleMenu> roleMenus = this.roleMenuMapper.roleCheckedMenus(roleId);
        return roleMenus.stream().map(RoleMenu::getMenuId).toArray(Long[]::new);
    }

    public void getChildren(List<Menu> menus, TreeVo treeVo) {
        List<TreeVo> treeList = new ArrayList<>();
        if (null != menus && menus.size() != 0) {
            menus.stream().forEach(menu -> {
                if (menu.getParentId().equals(treeVo.getId())) {
                    TreeVo vo = new TreeVo(menu.getMenuId(), menu.getMenuName());
                    getChildren(menus, vo);
                    treeList.add(vo);
                }
            });
        }
        if (treeList.size() != 0) {
            treeVo.setChildren(treeList);
        }
    }
}
