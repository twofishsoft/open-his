package com.twofish.base;

import com.twofish.domain.User;
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
    User getOne(Long id);
}
