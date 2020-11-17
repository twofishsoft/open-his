package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderBackfeeItem;
import com.twofish.dto.OrderBackfeeItemDto;
import com.twofish.mapper.OrderBackfeeItemMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.OrderBackfeeItemService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【退费订单详情表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class OrderBackfeeItemServiceImpl implements OrderBackfeeItemService {

    @Resource
    private OrderBackfeeItemMapper orderBackfeeItemMapper;

    @Override
    public DataGridView listPage(OrderBackfeeItemDto orderBackfeeItemDto) {
        Page<OrderBackfeeItem> page = new Page<>(orderBackfeeItemDto.getPageNum(), orderBackfeeItemDto.getPageSize());
        QueryWrapper<OrderBackfeeItem> qw = new QueryWrapper<>();
        orderBackfeeItemMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<OrderBackfeeItem> selectAll() {
        QueryWrapper<OrderBackfeeItem> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return orderBackfeeItemMapper.selectList(qw);
    }

    @Override
    public int insert(OrderBackfeeItemDto orderBackfeeItemDto) {
        OrderBackfeeItem orderBackfeeItem = new OrderBackfeeItem();
        BeanUtil.copyProperties(orderBackfeeItemDto, orderBackfeeItem);
        return orderBackfeeItemMapper.insert(orderBackfeeItem);
    }

    @Override
    public int update(OrderBackfeeItemDto orderBackfeeItemDto) {
        OrderBackfeeItem orderBackfeeItem = new OrderBackfeeItem();
        BeanUtil.copyProperties(orderBackfeeItemDto, orderBackfeeItem);
        return orderBackfeeItemMapper.updateById(orderBackfeeItem);
    }

    @Override
    public int update(OrderBackfeeItem orderBackfeeItem) {
        return orderBackfeeItemMapper.updateById(orderBackfeeItem);
    }

    @Override
    public int deleteByIds(Long[] orderBackfeeItemIds) {
        List<Long> ids = Arrays.asList(orderBackfeeItemIds);
        if(ids != null && ids.size() > 0){
        return orderBackfeeItemMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public OrderBackfeeItem getOneById(Long id) {
        return orderBackfeeItemMapper.selectById(id);
    }

    @Override
    public List<OrderBackfeeItem> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderBackfeeItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeItemMapper.selectList(qw);
    }

    @Override
    public OrderBackfeeItem getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<OrderBackfeeItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeItemMapper.selectOne(qw);
    }

}