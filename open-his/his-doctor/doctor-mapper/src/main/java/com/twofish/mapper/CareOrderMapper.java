package com.twofish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.CareOrder;
import com.twofish.dto.CareOrderDto;

import java.util.List;

public interface CareOrderMapper extends BaseMapper<CareOrder> {

    List<CareOrder> getNoChargeCareByRegId(CareOrderDto careOrderDto);
}