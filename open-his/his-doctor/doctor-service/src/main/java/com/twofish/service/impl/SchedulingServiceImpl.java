package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Scheduling;
import com.twofish.dto.SchedulingDto;
import com.twofish.mapper.SchedulingMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.SchedulingService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【排班信息表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class SchedulingServiceImpl implements SchedulingService {

    @Resource
    private SchedulingMapper schedulingMapper;

    @Override
    public DataGridView listPage(SchedulingDto schedulingDto) {
        Page<Scheduling> page = new Page<>(schedulingDto.getPageNum(), schedulingDto.getPageSize());
        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        schedulingMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Scheduling> selectAll() {
        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return schedulingMapper.selectList(qw);
    }

    @Override
    public int insert(SchedulingDto schedulingDto) {
        Scheduling scheduling = new Scheduling();
        BeanUtil.copyProperties(schedulingDto, scheduling);
        return schedulingMapper.insert(scheduling);
    }

    @Override
    public int update(SchedulingDto schedulingDto) {
        Scheduling scheduling = new Scheduling();
        BeanUtil.copyProperties(schedulingDto, scheduling);
        return schedulingMapper.updateById(scheduling);
    }

    @Override
    public int update(Scheduling scheduling) {
        return schedulingMapper.updateById(scheduling);
    }

    @Override
    public int deleteByIds(Long[] schedulingIds) {
        List<Long> ids = Arrays.asList(schedulingIds);
        if(ids != null && ids.size() > 0){
        return schedulingMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public Scheduling findById(String id) {
        return schedulingMapper.selectById(id);
    }

    @Override
    public List<Scheduling> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return schedulingMapper.selectList(qw);
    }

    @Override
    public List<Scheduling> listDeptForScheduling(SchedulingDto schedulingDto) {
        return schedulingMapper.listDeptForScheduling(schedulingDto);
    }

    @Override
    public Scheduling queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return schedulingMapper.selectOne(qw);
    }

}