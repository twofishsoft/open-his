package com.twofish.service;

import com.twofish.domain.Dept;
import com.twofish.domain.User;
import com.twofish.dto.UserDto;
import com.twofish.vo.DataGridView;

import java.util.List;

/**
 * 部门服务接口
 */
public interface DeptService {


    /**
     * 根据部门ID查询部门
     * @param deptId
     * @return
     */
    Dept getOne(Long deptId);

    /**
     * 查询所有的部门数据
     * @return
     */
    List<Dept> queryAllDept();
}
