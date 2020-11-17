package com.twofish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.Scheduling;
import com.twofish.dto.SchedulingDto;

import java.util.List;

public interface SchedulingMapper extends BaseMapper<Scheduling> {

    List<Scheduling> listDeptForScheduling(SchedulingDto schedulingDto);
}