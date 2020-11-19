package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.*;
import com.twofish.dto.*;
import com.twofish.mapper.OrderBackfeeMapper;
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
 * @description 【退费主表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class OrderBackfeeServiceImpl implements OrderBackfeeService {

    @Resource
    private OrderBackfeeMapper orderBackfeeMapper;
    @Resource
    private CareHistoryService careHistoryService;
    @Resource
    private CareOrderService careOrderService;
    @Resource
    private OrderChargeService orderChargeService;
    @Resource
    private OrderBackfeeItemService orderBackfeeItemService;

    @Override
    public DataGridView listPage(OrderBackfeeDto orderBackfeeDto) {
        Page<OrderBackfee> page = new Page<>(orderBackfeeDto.getPageNum(), orderBackfeeDto.getPageSize());
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(orderBackfeeDto.getPatientName()), OrderBackfee.COL_PATIENT_NAME, orderBackfeeDto.getPatientName());
        qw.eq(StringUtils.isNotBlank(orderBackfeeDto.getRegId()), OrderBackfee.COL_REG_ID, orderBackfeeDto.getRegId());
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
    public OrderBackfee findById(String id) {
        return orderBackfeeMapper.selectById(id);
    }

    @Override
    public List<OrderBackfee> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeMapper.selectList(qw);
    }

    @Override
    public OrderBackfee queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<OrderBackfee> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return orderBackfeeMapper.selectOne(qw);
    }

    @Override
    public ChargedCareHistoryDto getChargedCareHistoryByRegId(String regId) {
        ChargedCareHistoryDto chargedCareHistoryDto = new ChargedCareHistoryDto();
        CareHistory careHistory = careHistoryService.queryOneByAttr(CareHistory.COL_REG_ID, regId);
        if (null != careHistory) {
            CareOrderDto chargeDto = new CareOrderDto();
            chargeDto.setChId(careHistory.getChId());
            chargeDto.setOrderStatus(Constants.ORDER_DETAILS_STATUS_1);
            List<CareOrder> list = careOrderService.getCareOrderItem(chargeDto);
            chargedCareHistoryDto.setCareHistory(careHistory);
            chargedCareHistoryDto.setCareOrders(list);
        }
        return chargedCareHistoryDto;
    }

    @Override
    public int createOrderBackfeeWithCash(OrderBackfeeWithCashDto orderBackfeeDto, String type) {
        SimpleUser simpleUser = orderBackfeeDto.getSimpleUser();
        OrderBackfeeDto obj = orderBackfeeDto.getOrderBackfeeDto();
        List<OrderBackfeeItemDto> list = orderBackfeeDto.getOrderBackfeeItemDtoList();

        OrderBackfee orderBackfee = new OrderBackfee();
        BeanUtil.copyProperties(obj, orderBackfee);
        orderBackfee.setBackStatus(Constants.ORDER_BACKFEE_STATUS_1);
        orderBackfee.setBackType(type);
        orderBackfee.setBackTime(new Date());
        orderBackfee.setBackPlatformId(UUID.randomUUID().toString().replace("_", ""));
        orderBackfee.setCreateBy(simpleUser.getUserName());
        orderBackfee.setUpdateBy(simpleUser.getUserName());
        OrderCharge orderCharge = orderChargeService.queryByChIdAndRegId(obj.getChId(), obj.getRegId());
        if (null != orderCharge) {
            orderBackfee.setOrderId(orderCharge.getOrderId());
        }
        int insert = orderBackfeeMapper.insert(orderBackfee);
        insert = orderBackfeeItemService.batchOrderBackfeeItem(list, orderBackfee.getBackId());
        return insert;
    }

}