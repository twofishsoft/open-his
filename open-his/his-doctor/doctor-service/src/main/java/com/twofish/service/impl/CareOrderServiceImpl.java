package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CareOrder;
import com.twofish.dto.CareOrderDto;
import com.twofish.mapper.CareOrderMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.CareOrderService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【药用处方表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class CareOrderServiceImpl implements CareOrderService {

    @Resource
    private CareOrderMapper careOrderMapper;

    @Override
    public DataGridView listPage(CareOrderDto careOrderDto) {
        Page<CareOrder> page = new Page<>(careOrderDto.getPageNum(), careOrderDto.getPageSize());
        QueryWrapper<CareOrder> qw = new QueryWrapper<>();
        careOrderMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<CareOrder> selectAll() {
        QueryWrapper<CareOrder> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return careOrderMapper.selectList(qw);
    }

    @Override
    public int insert(CareOrderDto careOrderDto) {
        CareOrder careOrder = new CareOrder();
        BeanUtil.copyProperties(careOrderDto, careOrder);
        return careOrderMapper.insert(careOrder);
    }

    @Override
    public int update(CareOrderDto careOrderDto) {
        CareOrder careOrder = new CareOrder();
        BeanUtil.copyProperties(careOrderDto, careOrder);
        return careOrderMapper.updateById(careOrder);
    }

    @Override
    public int update(CareOrder careOrder) {
        return careOrderMapper.updateById(careOrder);
    }

    @Override
    public int deleteByIds(Long[] careOrderIds) {
        List<Long> ids = Arrays.asList(careOrderIds);
        if(ids != null && ids.size() > 0){
        return careOrderMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public CareOrder getOneById(Long id) {
        return careOrderMapper.selectById(id);
    }

    @Override
    public List<CareOrder> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<CareOrder> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careOrderMapper.selectList(qw);
    }

    @Override
    public CareOrder getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CareOrder> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careOrderMapper.selectOne(qw);
    }

}