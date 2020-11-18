package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Patient;
import com.twofish.dto.PatientDto;
import com.twofish.mapper.PatientMapper;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import twofish.service.PatientService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【患者信息表】业务逻辑实现
 * @date 2020-11-17 16:35:08
 **/
@Service
public class PatientServiceImpl implements PatientService {

    @Resource
    private PatientMapper patientMapper;

    @Override
    public DataGridView listPage(PatientDto patientDto) {
        Page<Patient> page = new Page<>(patientDto.getPageNum(), patientDto.getPageSize());
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(patientDto.getName()), Patient.COL_NAME, patientDto.getName());
        qw.eq(StringUtils.isNotBlank(patientDto.getIdCard()), Patient.COL_ID_CARD, patientDto.getIdCard());
        qw.eq(StringUtils.isNotBlank(patientDto.getPhone()), Patient.COL_PHONE, patientDto.getPhone());
        qw.ge(null != patientDto.getBeginTime(), Patient.COL_CREATE_TIME, patientDto.getBeginTime());
        qw.le(null != patientDto.getEndTime(), Patient.COL_CREATE_TIME, patientDto.getEndTime());
        patientMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Patient> selectAll() {
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return patientMapper.selectList(qw);
    }

    @Override
    public int insert(PatientDto patientDto) {
        Patient patient = new Patient();
        BeanUtil.copyProperties(patientDto, patient);
        return patientMapper.insert(patient);
    }

    @Override
    public int update(PatientDto patientDto) {
        Patient patient = new Patient();
        BeanUtil.copyProperties(patientDto, patient);
        return patientMapper.updateById(patient);
    }

    @Override
    public int update(Patient patient) {
        return patientMapper.updateById(patient);
    }

    @Override
    public int deleteByIds(Long[] patientIds) {
        List<Long> ids = Arrays.asList(patientIds);
        if(ids != null && ids.size() > 0){
        return patientMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public Patient findById(String id) {
        return patientMapper.selectById(id);
    }

    @Override
    public List<Patient> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return patientMapper.selectList(qw);
    }

    @Override
    public Patient queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return patientMapper.selectOne(qw);
    }

    @Override
    public Patient getPatientByIdCard(String idCard) {
        QueryWrapper<Patient> qw = new QueryWrapper<>();
        qw.eq(Patient.COL_ID_CARD, idCard);
        return patientMapper.selectOne(qw);
    }
}