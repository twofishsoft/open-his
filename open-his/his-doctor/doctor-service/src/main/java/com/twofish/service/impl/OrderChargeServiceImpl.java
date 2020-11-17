package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.OrderCharge;
import com.twofish.dto.OrderChargeDto;
import com.twofish.mapper.OrderChargeMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.OrderChargeService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【收费表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class OrderChargeServiceImpl implements OrderChargeService {

    @Resource
    private OrderChargeMapper orderChargeMapper;

    @Override
    public DataGridView listPage(OrderChargeDto orderChargeDto) {
        Page<OrderCharge> page = new Page<>(orderChargeDto.getPageNum(), orderChargeDto.getPageSize());
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        orderChargeMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<OrderCharge> selectAll() {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return orderChargeMapper.selectList(qw);
    }

    @Override
    public int insert(OrderChargeDto orderChargeDto) {
        OrderCharge orderCharge = new OrderCharge();
        BeanUtil.copyProperties(orderChargeDto, orderCharge);
        return orderChargeMapper.insert(orderCharge);
    }

    @Override
    public int update(OrderChargeDto orderChargeDto) {
        OrderCharge orderCharge = new OrderCharge();
        BeanUtil.copyProperties(orderChargeDto, orderCharge);
        return orderChargeMapper.updateById(orderCharge);
    }

    @Override
    public int update(OrderCharge orderCharge) {
        return orderChargeMapper.updateById(orderCharge);
    }

    @Override
    public int deleteByIds(Long[] orderChargeIds) {
        List<Long> ids = Arrays.asList(orderChargeIds);
        if(ids != null && ids.size() > 0){
        return orderChargeMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public OrderCharge getOneById(Long id) {
        return orderChargeMapper.selectById(id);
    }

    @Override
    public List<OrderCharge> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderChargeMapper.selectList(qw);
    }

    @Override
    public OrderCharge getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderChargeMapper.selectOne(qw);
    }

}