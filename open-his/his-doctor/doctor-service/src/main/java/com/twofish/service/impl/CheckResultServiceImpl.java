package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CheckResult;
import com.twofish.dto.CheckResultDto;
import com.twofish.mapper.CheckResultMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.CheckResultService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【检查结果】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class CheckResultServiceImpl implements CheckResultService {

    @Resource
    private CheckResultMapper checkResultMapper;

    @Override
    public DataGridView listPage(CheckResultDto checkResultDto) {
        Page<CheckResult> page = new Page<>(checkResultDto.getPageNum(), checkResultDto.getPageSize());
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        checkResultMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<CheckResult> selectAll() {
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return checkResultMapper.selectList(qw);
    }

    @Override
    public int insert(CheckResultDto checkResultDto) {
        CheckResult checkResult = new CheckResult();
        BeanUtil.copyProperties(checkResultDto, checkResult);
        return checkResultMapper.insert(checkResult);
    }

    @Override
    public int update(CheckResultDto checkResultDto) {
        CheckResult checkResult = new CheckResult();
        BeanUtil.copyProperties(checkResultDto, checkResult);
        return checkResultMapper.updateById(checkResult);
    }

    @Override
    public int update(CheckResult checkResult) {
        return checkResultMapper.updateById(checkResult);
    }

    @Override
    public int deleteByIds(Long[] checkResultIds) {
        List<Long> ids = Arrays.asList(checkResultIds);
        if(ids != null && ids.size() > 0){
        return checkResultMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public CheckResult getOneById(Long id) {
        return checkResultMapper.selectById(id);
    }

    @Override
    public List<CheckResult> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return checkResultMapper.selectList(qw);
    }

    @Override
    public CheckResult getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return checkResultMapper.selectOne(qw);
    }

}