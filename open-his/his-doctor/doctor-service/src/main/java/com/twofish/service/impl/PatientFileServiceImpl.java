package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.PatientFile;
import com.twofish.dto.PatientFileDto;
import com.twofish.mapper.PatientFileMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
import twofish.service.PatientFileService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【患者档案】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class PatientFileServiceImpl implements PatientFileService {

    @Resource
    private PatientFileMapper patientFileMapper;

    @Override
    public DataGridView listPage(PatientFileDto patientFileDto) {
        Page<PatientFile> page = new Page<>(patientFileDto.getPageNum(), patientFileDto.getPageSize());
        QueryWrapper<PatientFile> qw = new QueryWrapper<>();
        patientFileMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<PatientFile> selectAll() {
        QueryWrapper<PatientFile> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return patientFileMapper.selectList(qw);
    }

    @Override
    public int insert(PatientFileDto patientFileDto) {
        PatientFile patientFile = new PatientFile();
        BeanUtil.copyProperties(patientFileDto, patientFile);
        return patientFileMapper.insert(patientFile);
    }

    @Override
    public int update(PatientFileDto patientFileDto) {
        PatientFile patientFile = new PatientFile();
        BeanUtil.copyProperties(patientFileDto, patientFile);
        return patientFileMapper.updateById(patientFile);
    }

    @Override
    public int update(PatientFile patientFile) {
        return patientFileMapper.updateById(patientFile);
    }

    @Override
    public int deleteByIds(Long[] patientFileIds) {
        List<Long> ids = Arrays.asList(patientFileIds);
        if(ids != null && ids.size() > 0){
        return patientFileMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public PatientFile getOneById(String id) {
        return patientFileMapper.selectById(id);
    }

    @Override
    public List<PatientFile> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<PatientFile> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return patientFileMapper.selectList(qw);
    }

    @Override
    public PatientFile getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<PatientFile> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return patientFileMapper.selectOne(qw);
    }

}