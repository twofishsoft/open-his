package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.Dept;
import com.twofish.dto.DeptDto;
import com.twofish.mapper.DeptMapper;
import com.twofish.service.DeptService;
import com.twofish.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 部门服务实现
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, DeptDto> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public BaseMapper getMapper() {
        return deptMapper;
    }
}
