package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.RoleUser;
import com.twofish.mapper.RoleUserMapper;
import com.twofish.service.RoleService;
import com.twofish.service.RoleUserService;
import com.twofish.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 用户角色服务实现
 */
@Service
public class RoleUserServiceImpl extends BaseServiceImpl<RoleUser> implements RoleUserService {

    @Resource
    private RoleUserMapper roleUserMapper;

    @Override
    public BaseMapper getMapper() {
        return roleUserMapper;
    }

}
