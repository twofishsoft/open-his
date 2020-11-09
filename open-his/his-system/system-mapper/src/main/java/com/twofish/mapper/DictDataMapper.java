package com.twofish.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twofish.domain.DictData;
import com.twofish.dto.DicDataDto;

public interface DictDataMapper extends BaseMapper<DictData> {

    DictData queryDataByTypeAndValue(DicDataDto dicDataDto);
}