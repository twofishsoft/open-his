package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.RegisteredItem;
import com.twofish.dto.RegisteredItemDto;
import com.twofish.mapper.RegisteredItemMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.RegisteredItemService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【挂号项目】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class RegisteredItemServiceImpl implements RegisteredItemService {

    @Resource
    private RegisteredItemMapper registeredItemMapper;

    @Override
    public DataGridView listPage(RegisteredItemDto registeredItemDto) {
        Page<RegisteredItem> page = new Page<>(registeredItemDto.getPageNum(), registeredItemDto.getPageSize());
        QueryWrapper<RegisteredItem> qw = new QueryWrapper<>();
        registeredItemMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<RegisteredItem> selectAll() {
        QueryWrapper<RegisteredItem> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return registeredItemMapper.selectList(qw);
    }

    @Override
    public int insert(RegisteredItemDto registeredItemDto) {
        RegisteredItem registeredItem = new RegisteredItem();
        BeanUtil.copyProperties(registeredItemDto, registeredItem);
        return registeredItemMapper.insert(registeredItem);
    }

    @Override
    public int update(RegisteredItemDto registeredItemDto) {
        RegisteredItem registeredItem = new RegisteredItem();
        BeanUtil.copyProperties(registeredItemDto, registeredItem);
        return registeredItemMapper.updateById(registeredItem);
    }

    @Override
    public int update(RegisteredItem registeredItem) {
        return registeredItemMapper.updateById(registeredItem);
    }

    @Override
    public int deleteByIds(Long[] registeredItemIds) {
        List<Long> ids = Arrays.asList(registeredItemIds);
        if(ids != null && ids.size() > 0){
        return registeredItemMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public RegisteredItem findById(String id) {
        return registeredItemMapper.selectById(id);
    }

    @Override
    public List<RegisteredItem> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<RegisteredItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return registeredItemMapper.selectList(qw);
    }

    @Override
    public RegisteredItem queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<RegisteredItem> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return registeredItemMapper.selectOne(qw);
    }

}