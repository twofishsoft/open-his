package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CareHistory;
import com.twofish.domain.CareOrder;
import com.twofish.domain.CareOrderItem;
import com.twofish.dto.CareHistoryDto;
import com.twofish.dto.CareOrderDto;
import com.twofish.dto.CareOrdersDto;
import com.twofish.dto.PatientAllMessageDto;
import com.twofish.mapper.CareHistoryMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【病例表】业务逻辑实现
 * @date 2020-11-17 16:30:29
 **/
@Service
public class CareHistoryServiceImpl implements CareHistoryService {

    @Resource
    private CareHistoryMapper careHistoryMapper;
    @Resource
    private CareOrderService careOrderService;
    @Resource
    private CareOrderItemService careOrderItemService;
    @Resource
    private PatientService patientService;
    @Resource
    private PatientFileService patientFileService;

    @Override
    public DataGridView listPage(CareHistoryDto careHistoryDto) {
        Page<CareHistory> page = new Page<>(careHistoryDto.getPageNum(), careHistoryDto.getPageSize());
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        careHistoryMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<CareHistory> selectAll() {
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return careHistoryMapper.selectList(qw);
    }

    @Override
    public int insert(CareHistoryDto careHistoryDto) {
        CareHistory careHistory = new CareHistory();
        BeanUtil.copyProperties(careHistoryDto, careHistory);
        return careHistoryMapper.insert(careHistory);
    }

    @Override
    public int update(CareHistoryDto careHistoryDto) {
        CareHistory careHistory = new CareHistory();
        BeanUtil.copyProperties(careHistoryDto, careHistory);
        return careHistoryMapper.updateById(careHistory);
    }

    @Override
    public int update(CareHistory careHistory) {
        return careHistoryMapper.updateById(careHistory);
    }

    @Override
    public int deleteByIds(Long[] careHistoryIds) {
        List<Long> ids = Arrays.asList(careHistoryIds);
        if(ids != null && ids.size() > 0){
        return careHistoryMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public CareHistory findById(String id) {
        return careHistoryMapper.selectById(id);
    }

    @Override
    public List<CareHistory> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careHistoryMapper.selectList(qw);
    }

    @Override
    public CareHistory queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careHistoryMapper.selectOne(qw);
    }

    @Override
    public List<CareHistory> getPatientAllMessageByPatientId(String patientId) {
        List<CareHistory> list = queryByAttrList(CareHistory.COL_PATIENT_ID, patientId);
        if (null != list && list.size() != 0) {
            list.forEach(item -> {
                CareOrderDto careOrderDto = new CareOrderDto();
                careOrderDto.setChId(item.getChId());
                item.setCareOrders(careOrderService.getCareOrderItem(careOrderDto));
            });
        }
        return list;
    }

    @Override
    public PatientAllMessageDto getPatientAllMessageByPatientIds(String patientId) {
        PatientAllMessageDto dto = new PatientAllMessageDto();
        dto.setCareHistoryList(getPatientAllMessageByPatientId(patientId));
        dto.setPatient(patientService.findById(patientId));
        dto.setPatientFile(patientFileService.findById(patientId));
        return dto;
    }

    @Override
    public CareOrdersDto queryCareOrdersByChId(String chId) {
        CareOrdersDto dto = new CareOrdersDto();
        CareOrder careOrder = careOrderService.queryOneByAttr(CareOrder.COL_CH_ID, chId);
        List<CareOrderItem> list = careOrderItemService.queryByAttrList(CareOrderItem.COL_CO_ID, careOrder.getCoId());
        dto.setCareOrder(careOrder);
        dto.setCareOrderItems(list);
        return dto;
    }

}