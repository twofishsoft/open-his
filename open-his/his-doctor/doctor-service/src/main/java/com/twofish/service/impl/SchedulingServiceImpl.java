package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Scheduling;
import com.twofish.dto.SchedulingDto;
import com.twofish.dto.SchedulingInfoDto;
import com.twofish.mapper.SchedulingMapper;
import com.twofish.utils.WeekUtil;
import com.twofish.vo.DataGridView;
import com.twofish.vo.SchedulingDataDto;
import com.twofish.vo.TableDataDto;
import org.springframework.stereotype.Service;
import twofish.service.SchedulingService;
import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public int saveScheduling(SchedulingDto schedulingDto) {
        String beginDate = schedulingDto.getBeginDate();
        List<TableDataDto> data = schedulingDto.getData();
        final int[] i = {-1};
        data.forEach(item -> {
            Scheduling scheduling = new Scheduling();
            BeanUtil.copyProperties(item, scheduling);
            scheduling.setSchedulingDay(beginDate);
            i[0] = schedulingMapper.insert(scheduling);
        });
        return i[0];
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

    @Override
    public SchedulingInfoDto queryScheduling(SchedulingDto schedulingDto) {
        Set set = new HashSet();
        List<TableDataDto> list = new ArrayList<>();
        SchedulingInfoDto schedulingInfoDto = new SchedulingInfoDto();
        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        qw.eq(null != schedulingDto.getDeptId(), Scheduling.COL_DEPT_ID, schedulingDto.getDeptId());
        qw.eq(null != schedulingDto.getUserId(), Scheduling.COL_USER_ID, schedulingDto.getUserId());
        qw.eq(null != schedulingDto.getQueryDate(), Scheduling.COL_SCHEDULING_DAY, schedulingDto.getQueryDate());
        List<Scheduling> schedulings = schedulingMapper.selectList(qw);;
        if (null != schedulings && schedulings.size() != 0) {
            schedulings.forEach(item -> {
                set.add(item.getSchedulingDay());
                TableDataDto tableDataDto = new TableDataDto();
                tableDataDto.setUserId(item.getUserId());
                tableDataDto.setDeptId(item.getDeptId());
                tableDataDto.setSchedulingType(new String[]{item.getSchedulingType()});
                tableDataDto.setSubsectionType(item.getSubsectionType());
                list.add(tableDataDto);
            });
        }
        List<String> labelNames = new ArrayList<>();
        List<String> schedulingDay = new ArrayList<>(set);
        schedulingDay.forEach(item -> {
            labelNames.add(item + "(" + WeekUtil.dateToWeek(item) + ")");
        });
        schedulingInfoDto.setLabelNames(labelNames);
        schedulingInfoDto.setTableData(list);
        schedulingInfoDto.setSchedulingData(WeekUtil.getClock(schedulingDay));
        return schedulingInfoDto;
    }

    @Override
    public SchedulingInfoDto queryMyScheduling(SchedulingDto schedulingDto) {
        List<TableDataDto> list = new ArrayList<>();
        SchedulingInfoDto schedulingInfoDto = new SchedulingInfoDto();
        Set set = new HashSet();

        QueryWrapper<Scheduling> qw = new QueryWrapper<>();
        qw.eq(Scheduling.COL_USER_ID, schedulingDto.getUserId());
        qw.eq(null != schedulingDto.getQueryDate(), Scheduling.COL_SCHEDULING_DAY, schedulingDto.getQueryDate());
        List<Scheduling> schedulings = schedulingMapper.selectList(qw);
        if (null != schedulings && schedulings.size() != 0) {
            schedulings.forEach(item -> {
                set.add(item.getSchedulingDay());
                TableDataDto tableDataDto = new TableDataDto();
                tableDataDto.setUserId(item.getUserId());
                tableDataDto.setDeptId(item.getDeptId());
                tableDataDto.setSchedulingType(new String[]{item.getSchedulingType()});
                tableDataDto.setSubsectionType(item.getSubsectionType());
                list.add(tableDataDto);
            });
        }
        List<String> labelNames = new ArrayList<>();
        List<String> schedulingDay = new ArrayList<>(set);
        schedulingDay.forEach(item -> {
            labelNames.add(item + "(" + WeekUtil.dateToWeek(item) + ")");
        });
        schedulingInfoDto.setLabelNames(labelNames);
        schedulingInfoDto.setTableData(list);
        schedulingInfoDto.setSchedulingData(WeekUtil.getClock(schedulingDay));
        return schedulingInfoDto;
    }

}