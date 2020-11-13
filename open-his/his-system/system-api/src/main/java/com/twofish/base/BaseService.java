package com.twofish.base;

import com.twofish.vo.BaseDto;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/12 23:27
 */
public interface BaseService<T> {

    /**
     * 查询所有可用信息
     * @return
     */
    List<T> selectAll();

    /**
     * 添加
     * @param baseDto
     * @return
     */
    int insert(BaseDto baseDto);

    /**
     * 修改
     * @param baseDto
     * @return
     */
    int update(BaseDto baseDto);

    /**
     * 根据ID修改
     * @param t
     * @return
     */
    int update(T t);

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
    T getOneById(Long id);

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    List<T> findByAttr(String attr, Object attrValue);

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    T getOneByAttr(String attr, Object attrValue);
}
