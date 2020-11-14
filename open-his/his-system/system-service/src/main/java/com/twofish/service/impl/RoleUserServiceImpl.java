package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.constants.Constants;
import com.twofish.domain.RoleUser;
import com.twofish.dto.RoleUserDto;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.service.RoleUserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 用户角色服务实现
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    /**
     * 查找所有有效数据
     * @return
     */
    public List<RoleUser> selectAll() {
        QueryWrapper<RoleUser> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return roleUserMapper.selectList(qw);
    }

    /**
     * 添加数据
     * @param roleUserDto
     * @return
     */
    public int insert(RoleUserDto roleUserDto) {
        RoleUser roleUser = new RoleUser();
        BeanUtil.copyProperties(roleUserDto, roleUser);
        return roleUserMapper.insert(roleUser);
    }

    /**
     * 修改数据
     * @param roleUserDto
     * @return
     */
    public int update(RoleUserDto roleUserDto) {
        RoleUser roleUser = new RoleUser();
        BeanUtil.copyProperties(roleUserDto, roleUser);
        return roleUserMapper.updateById(roleUser);
    }

    public int update(RoleUser roleUser) {
        return roleUserMapper.updateById(roleUser);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return roleUserMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    public RoleUser getOneById(Long id) {
        return roleUserMapper.selectById(id);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public List<RoleUser> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<RoleUser> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return roleUserMapper.selectList(qw);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public RoleUser getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<RoleUser> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return roleUserMapper.selectOne(qw);
    }

}
