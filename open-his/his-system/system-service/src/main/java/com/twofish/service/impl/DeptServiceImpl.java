package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twofish.constants.Constants;
import com.twofish.domain.Dept;
import com.twofish.dto.DeptDto;
import com.twofish.mapper.DeptMapper;
import com.twofish.service.DeptService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 部门服务实现
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    /**
     * 查找所有有效数据
     * @return
     */
    public List<Dept> selectAll() {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return deptMapper.selectList(qw);
    }

    /**
     * 添加数据
     * @param deptDto
     * @return
     */
    public int insert(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanUtil.copyProperties(deptDto, dept);
        return deptMapper.insert(dept);
    }

    /**
     * 修改数据
     * @param deptDto
     * @return
     */
    public int update(DeptDto deptDto) {
        Dept dept = new Dept();
        BeanUtil.copyProperties(deptDto, dept);
        return deptMapper.updateById(dept);
    }

    public int update(Dept dept) {
        return deptMapper.updateById(dept);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return deptMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    public Dept getOneById(Long id) {
        return deptMapper.selectById(id);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public List<Dept> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return deptMapper.selectList(qw);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public Dept getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<Dept> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return deptMapper.selectOne(qw);
    }
}
