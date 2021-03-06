package com.twofish.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询
     * @param page 分页实体
     * @param wrapper 条件构造器
     * @return Page实体
     */
    Page<User> getUserAndDeptNamePage(Page page, @Param("ew") Wrapper wrapper);
}