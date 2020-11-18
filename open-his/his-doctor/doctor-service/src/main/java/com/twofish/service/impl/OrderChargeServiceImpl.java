package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.twofish.constants.Constants;
import com.twofish.domain.CareHistory;
import com.twofish.domain.OrderCharge;
import com.twofish.domain.Registration;
import com.twofish.dto.OrderChargeDto;
import com.twofish.mapper.OrderChargeMapper;
import com.twofish.vo.DataGridView;
import io.netty.util.Constant;
import org.springframework.stereotype.Service;
import twofish.service.CareHistoryService;
import twofish.service.OrderChargeService;
import twofish.service.RegistrationService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
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
    @Resource
    private RegistrationService registrationService;
    @Resource
    private CareHistoryService careHistoryService;

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
    public OrderCharge getOneById(String id) {
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

    @Override
    public OrderCharge queryByChIdAndRegId(String chId, String regId) {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(OrderCharge.COL_CH_ID, chId);
        qw.eq(OrderCharge.COL_REG_ID, regId);
        return orderChargeMapper.selectOne(qw);
    }

    @Override
    public int collectFee(String regId, OrderChargeDto orderChargeDto) {
        Registration registration = registrationService.getOneById(regId);
        if (null != registration) {
            CareHistory careHistory = careHistoryService.getOneByAttr(CareHistory.COL_REG_ID, regId);
            orderChargeDto.setOrderAmount(registration.getRegistrationAmount());
            orderChargeDto.setRegId(regId.toString());
            orderChargeDto.setPatientName(registration.getPatientName());
            orderChargeDto.setPayPlatformId(System.currentTimeMillis() + "");
            orderChargeDto.setPayTime(new Date());
            orderChargeDto.setPayType(Constants.PAY_TYPE_0);
            orderChargeDto.setOrderStatus(Constants.ORDER_STATUS_1);
            if (null != careHistory) {
                orderChargeDto.setChId(careHistory.getChId());
            }
            insert(orderChargeDto);
        }
        return 0;
    }

    @Override
    public OrderChargeDto getChargedCareHistoryByRegId(String regId) {
        return null;
    }
}