package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CareHistory;
import com.twofish.domain.CareOrder;
import com.twofish.dto.CareHistoryDto;
import com.twofish.mapper.CareHistoryMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.CareHistoryService;
import twofish.service.CareOrderService;

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
    public CareHistory getOneById(String id) {
        return careHistoryMapper.selectById(id);
    }

    @Override
    public List<CareHistory> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careHistoryMapper.selectList(qw);
    }

    @Override
    public CareHistory getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CareHistory> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careHistoryMapper.selectOne(qw);
    }

    @Override
    public List<CareHistory> getPatientAllMessageByPatientId(Long patientId) {
        List<CareHistory> list = findByAttrList(CareHistory.COL_PATIENT_ID, patientId);
        if (null != list && list.size() != 0) {
            list.forEach(item -> {
                item.setCareOrders(careOrderService.getCareOrderItem(item.getChId()));
            });
        }
        return list;
    }

}