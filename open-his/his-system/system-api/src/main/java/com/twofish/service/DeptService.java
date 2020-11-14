package com.twofish.service;

import com.twofish.domain.Dept;
import com.twofish.dto.DeptDto;
import java.util.List;

/**
 * 部门服务接口
 */
public interface DeptService {

    /**
     * 查询所有可用信息
     * @return
     */
    List<Dept> selectAll();

    /**
     * 添加
     * @param deptDto
     * @return
     */
    int insert(DeptDto deptDto);

    /**
     * 修改
     * @param deptDto
     * @return
     */
    int update(DeptDto deptDto);

    /**
     * 根据ID修改
     * @param dept
     * @return
     */
    int update(Dept dept);

    /**
     * 删除数据(可批量删除)
     * @param ids
     * @return
     */
    int deleteByIds(Long[] ids);

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    Dept getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<Dept> findByAttrList(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    Dept getOneByAttr(String attr, Object attrValue);

}
