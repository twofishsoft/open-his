package com.twofish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.RoleUser;

import java.util.List;

public interface RoleUserMapper extends BaseMapper<RoleUser> {

    int deleteRoleUserByUserId(Long userId);

    int batchUserRole(List<RoleUser> roleUserList);
}