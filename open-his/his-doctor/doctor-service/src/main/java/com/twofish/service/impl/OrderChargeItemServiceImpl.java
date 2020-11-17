package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderChargeItem;
import com.twofish.dto.OrderChargeItemDto;
import com.twofish.mapper.OrderChargeItemMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.OrderChargeItemService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【支付订单详情表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class OrderChargeItemServiceImpl implements OrderChargeItemService {

    @Resource
    private OrderChargeItemMapper orderChargeItemMapper;

    @Override
    public DataGridView listPage(OrderChargeItemDto orderChargeItemDto) {
        Page<OrderChargeItem> page = new Page<>(orderChargeItemDto.getPageNum(), orderChargeItemDto.getPageSize());
        QueryWrapper<OrderChargeItem> qw = new QueryWrapper<>();
        orderChargeItemMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<OrderChargeItem> selectAll() {
        QueryWrapper<OrderChargeItem> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return orderChargeItemMapper.selectList(qw);
    }

    @Override
    public int insert(OrderChargeItemDto orderChargeItemDto) {
        OrderChargeItem orderChargeItem = new OrderChargeItem();
        BeanUtil.copyProperties(orderChargeItemDto, orderChargeItem);
        return orderChargeItemMapper.insert(orderChargeItem);
    }

    @Override
    public int update(OrderChargeItemDto orderChargeItemDto) {
        OrderChargeItem orderChargeItem = new OrderChargeItem();
        BeanUtil.copyProperties(orderChargeItemDto, orderChargeItem);
        return orderChargeItemMapper.updateById(orderChargeItem);
    }

    @Override
    public int update(OrderChargeItem orderChargeItem) {
        return orderChargeItemMapper.updateById(orderChargeItem);
    }

    @Override
    public int deleteByIds(Long[] orderChargeItemIds) {
        List<Long> ids = Arrays.asList(orderChargeItemIds);
        if(ids != null && ids.size() > 0){
        return orderChargeItemMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public OrderChargeItem getOneById(Long id) {
        return orderChargeItemMapper.selectById(id);
    }

    @Override
    public List<OrderChargeItem> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderChargeItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderChargeItemMapper.selectList(qw);
    }

    @Override
    public OrderChargeItem getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<OrderChargeItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderChargeItemMapper.selectOne(qw);
    }

}