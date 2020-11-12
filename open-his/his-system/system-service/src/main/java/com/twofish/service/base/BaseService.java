package com.twofish.service.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.constants.Constants;
import com.twofish.domain.BaseEntity;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/12 18:01
 */
public abstract class BaseService<T extends BaseEntity> {

    public abstract BaseMapper getMapper();

    /**
     * 查找所有
     * @return
     */
    public List<T> selectAll() {
        QueryWrapper<T> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return getMapper().selectList(qw);
    }

    /**
     * 批量删除
     * @param userIds
     * @return
     */
    public int deleteUserByIds(Long[] userIds) {
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
    public T getOne(Long id) {
        return (T) getMapper().selectById(id);
    }

}
