package com.twofish.service.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.BaseEntity;
import com.twofish.domain.User;
import com.twofish.vo.BaseDto;
import com.twofish.vo.DataGridView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/12 18:01
 */
public abstract class BaseServiceImpl<T extends BaseEntity, D extends BaseDto> {

    public abstract BaseMapper getMapper();

    public T getClazz() {
        Class<T> tClass = null;
        Type type = this.getClass().getGenericSuperclass();
        if(type instanceof ParameterizedType){
            tClass = (Class<T>)((ParameterizedType) type).getActualTypeArguments()[0];
        }
        try {
            return tClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 分页查询
     * @param baseDto
     * @return
     */
    public DataGridView listPage(D baseDto) {
        Page<T> page = new Page<>(baseDto.getPageNum(), baseDto.getPageSize());
        QueryWrapper<T> qw = new QueryWrapper<>();
        getMapper().selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    /**
     * 查找所有有效数据
     * @return
     */
    public List<T> selectAll() {
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return getMapper().selectList(qw);
    }

    /**
     * 添加数据
     * @param baseDto
     * @return
     */
    public int insert(D baseDto) {
        T t = getClazz();
        BeanUtil.copyProperties(baseDto, t);
        return getMapper().insert(t);
    }

    /**
     * 修改数据
     * @param baseDto
     * @return
     */
    public int update(D baseDto) {
        T t = getClazz();
        BeanUtil.copyProperties(baseDto, t);
        return getMapper().updateById(t);
    }

    public int update(T t) {
        return getMapper().updateById(t);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteByIds(Long[] userIds) {
        List<Long> ids = Arrays.asList(userIds);
        if(ids != null && ids.size() > 0){
            return getMapper().deleteBatchIds(ids);
        }
        return -1;
    }

    /**
     * 查找单条数据
     * @param id
     * @return
     */
    public T getOneById(Long id) {
        return (T) getMapper().selectById(id);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回集合
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public List<T> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return getMapper().selectList(qw);
    }

    /**
     * 根据对象中的某个属性，查询数据，返回单个数据
     * @param attr 对象中的某个字段
     * @param attrValue 字段值
     * @return
     */
    public T getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return (T) getMapper().selectOne(qw);
    }
}
