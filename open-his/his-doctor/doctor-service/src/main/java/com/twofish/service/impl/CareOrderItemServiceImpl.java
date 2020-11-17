package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CareOrderItem;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.mapper.CareOrderItemMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.CareOrderItemService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【开诊细表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class CareOrderItemServiceImpl implements CareOrderItemService {

    @Resource
    private CareOrderItemMapper careOrderItemMapper;

    @Override
    public DataGridView listPage(CareOrderItemDto careOrderItemDto) {
        Page<CareOrderItem> page = new Page<>(careOrderItemDto.getPageNum(), careOrderItemDto.getPageSize());
        QueryWrapper<CareOrderItem> qw = new QueryWrapper<>();
        careOrderItemMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<CareOrderItem> selectAll() {
        QueryWrapper<CareOrderItem> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return careOrderItemMapper.selectList(qw);
    }

    @Override
    public int insert(CareOrderItemDto careOrderItemDto) {
        CareOrderItem careOrderItem = new CareOrderItem();
        BeanUtil.copyProperties(careOrderItemDto, careOrderItem);
        return careOrderItemMapper.insert(careOrderItem);
    }

    @Override
    public int update(CareOrderItemDto careOrderItemDto) {
        CareOrderItem careOrderItem = new CareOrderItem();
        BeanUtil.copyProperties(careOrderItemDto, careOrderItem);
        return careOrderItemMapper.updateById(careOrderItem);
    }

    @Override
    public int update(CareOrderItem careOrderItem) {
        return careOrderItemMapper.updateById(careOrderItem);
    }

    @Override
    public int deleteByIds(Long[] careOrderItemIds) {
        List<Long> ids = Arrays.asList(careOrderItemIds);
        if(ids != null && ids.size() > 0){
        return careOrderItemMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public CareOrderItem getOneById(Long id) {
        return careOrderItemMapper.selectById(id);
    }

    @Override
    public List<CareOrderItem> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<CareOrderItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careOrderItemMapper.selectList(qw);
    }

    @Override
    public CareOrderItem getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CareOrderItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return careOrderItemMapper.selectOne(qw);
    }

}