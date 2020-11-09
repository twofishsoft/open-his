package com.twofish.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.constants.Constants;
import com.twofish.domain.Dept;
import com.twofish.mapper.DeptMapper;
import com.twofish.service.DeptService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 部门服务实现
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Dept getOne(Long deptId) {
        return deptMapper.selectById(deptId);
    }

    @Override
    public List<Dept> queryAllDept() {
        QueryWrapper<Dept> qw = new QueryWrapper();
        qw.eq(Dept.COL_STATUS, Constants.STATUS_TRUE);
        return deptMapper.selectList(qw);
    }

}
