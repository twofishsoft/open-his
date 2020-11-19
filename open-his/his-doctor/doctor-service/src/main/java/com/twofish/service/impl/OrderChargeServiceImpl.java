package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.*;
import com.twofish.dto.*;
import com.twofish.mapper.OrderChargeMapper;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import twofish.service.*;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
    @Resource
    private CareOrderService careOrderService;
    @Resource
    private OrderChargeItemService orderChargeItemService;

    @Override
    public DataGridView listPage(OrderChargeDto orderChargeDto) {
        String patientName = orderChargeDto.getPatientName();
        String regId = orderChargeDto.getRegId();
        Page<OrderCharge> page = new Page<>(orderChargeDto.getPageNum(), orderChargeDto.getPageSize());
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(patientName), OrderCharge.COL_PATIENT_NAME, patientName);
        qw.eq(StringUtils.isNotBlank(regId), OrderCharge.COL_REG_ID, regId);
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
    public OrderCharge findById(String id) {
        return orderChargeMapper.selectById(id);
    }

    @Override
    public List<OrderCharge> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderChargeMapper.selectList(qw);
    }

    @Override
    public OrderCharge queryOneByAttr(String attr, Object attrValue) {
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
        Registration registration = registrationService.findById(regId);
        if (null != registration) {
            CareHistory careHistory = careHistoryService.queryOneByAttr(CareHistory.COL_REG_ID, regId);
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
            return insert(orderChargeDto);
        }
        return -1;
    }

    @Override
    public OrderChargeDto getChargedCareHistoryByRegId(String regId) {
        return null;
    }

    @Override
    public NoChargeCareHistoryDto getNoChargeCareHistoryByRegId(String regId) {
        NoChargeCareHistoryDto noChargeCareHistoryDto = new NoChargeCareHistoryDto();
        CareHistory careHistory = careHistoryService.queryOneByAttr(CareHistory.COL_REG_ID, regId);
        if (null != careHistory) {
            CareOrderDto chargeDto = new CareOrderDto();
            chargeDto.setChId(careHistory.getChId());
            chargeDto.setOrderStatus(Constants.ORDER_DETAILS_STATUS_0);
            List<CareOrder> list = careOrderService.getNoChargeCareByRegId(chargeDto);
            noChargeCareHistoryDto.setCareOrders(list);
            noChargeCareHistoryDto.setCareHistory(careHistory);
        }
        return noChargeCareHistoryDto;
    }

    @Override
    public int createOrderChargeWithCash(OrderChargeWithCashDto orderChargeWithCashDto, String type) {
        OrderChargeDto orderChargeDto = orderChargeWithCashDto.getOrderChargeDto();
        List<OrderChargeItemDto> list = orderChargeWithCashDto.getOrderChargeItemDto();
        SimpleUser simpleUser = orderChargeWithCashDto.getSimpleUser();
        OrderCharge orderCharge = new OrderCharge();
        BeanUtil.copyProperties(orderChargeDto, orderCharge);
        orderCharge.setOrderStatus(Constants.ORDER_BACKFEE_STATUS_1);
        orderCharge.setPayPlatformId(UUID.randomUUID().toString().replace("_", ""));
        orderCharge.setPayTime(new Date());
        orderCharge.setPayType(type);
        orderCharge.setCreateBy(simpleUser.getUserName());
        orderCharge.setUpdateBy(simpleUser.getUserName());
        int insert = orderChargeMapper.insert(orderCharge);
        insert = orderChargeItemService.batchOrderChargeItem(list, orderCharge.getOrderId());
        return insert;
    }

    @Override
    public List<OrderCharge> payWithCash(String orderId, String type) {
        QueryWrapper<OrderCharge> qw = new QueryWrapper<>();
        qw.eq(OrderCharge.COL_ORDER_ID, orderId);
        qw.eq(OrderCharge.COL_PAY_TYPE, type);
        return orderChargeMapper.selectList(qw);
    }

}