package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderBackfee;
import com.twofish.dto.OrderBackfeeDto;
import com.twofish.mapper.OrderBackfeeMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.OrderBackfeeService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【退费主表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class OrderBackfeeServiceImpl implements OrderBackfeeService {

    @Resource
    private OrderBackfeeMapper orderBackfeeMapper;

    @Override
    public DataGridView listPage(OrderBackfeeDto orderBackfeeDto) {
        Page<OrderBackfee> page = new Page<>(orderBackfeeDto.getPageNum(), orderBackfeeDto.getPageSize());
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        orderBackfeeMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<OrderBackfee> selectAll() {
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return orderBackfeeMapper.selectList(qw);
    }

    @Override
    public int insert(OrderBackfeeDto orderBackfeeDto) {
        OrderBackfee orderBackfee = new OrderBackfee();
        BeanUtil.copyProperties(orderBackfeeDto, orderBackfee);
        return orderBackfeeMapper.insert(orderBackfee);
    }

    @Override
    public int update(OrderBackfeeDto orderBackfeeDto) {
        OrderBackfee orderBackfee = new OrderBackfee();
        BeanUtil.copyProperties(orderBackfeeDto, orderBackfee);
        return orderBackfeeMapper.updateById(orderBackfee);
    }

    @Override
    public int update(OrderBackfee orderBackfee) {
        return orderBackfeeMapper.updateById(orderBackfee);
    }

    @Override
    public int deleteByIds(Long[] orderBackfeeIds) {
        List<Long> ids = Arrays.asList(orderBackfeeIds);
        if(ids != null && ids.size() > 0){
        return orderBackfeeMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public OrderBackfee getOneById(Long id) {
        return orderBackfeeMapper.selectById(id);
    }

    @Override
    public List<OrderBackfee> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeMapper.selectList(qw);
    }

    @Override
    public OrderBackfee getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeMapper.selectOne(qw);
    }

}