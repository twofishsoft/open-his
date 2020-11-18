package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Patient;
import com.twofish.domain.Registration;
import com.twofish.domain.Scheduling;
import com.twofish.dto.RegistrationDto;
import com.twofish.dto.SchedulingDto;
import com.twofish.mapper.CareHistoryMapper;
import com.twofish.mapper.PatientMapper;
import com.twofish.mapper.RegistrationMapper;
import com.twofish.mapper.SchedulingMapper;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import twofish.service.CareHistoryService;
import twofish.service.RegistrationService;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author ww
 * @description 【挂号信息】业务逻辑实现
 * @date 2020-11-17 14:23:06
 **/
@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Resource
    private RegistrationMapper registrationMapper;

    @Override
    public DataGridView listPage(RegistrationDto registrationDto) {
        Page<Registration> page = new Page<>(registrationDto.getPageNum(), registrationDto.getPageSize());
        QueryWrapper<Registration> qw = new QueryWrapper<>();
        qw.eq(StringUtils.isNotBlank(registrationDto.getRegistrationStatus()), Registration.COL_REGISTRATION_STATUS, registrationDto.getRegistrationStatus());
        qw.eq(null != registrationDto.getDeptId(), Registration.COL_DEPT_ID, registrationDto.getDeptId());
        qw.eq(StringUtils.isNotBlank(registrationDto.getSubsectionType()), Registration.COL_SUBSECTION_TYPE, registrationDto.getSubsectionType());
        qw.eq(StringUtils.isNotBlank(registrationDto.getSchedulingType()), Registration.COL_SCHEDULING_TYPE, registrationDto.getSchedulingType());
        qw.eq(StringUtils.isNotBlank(registrationDto.getVisitDate()), Registration.COL_VISIT_DATE, registrationDto.getVisitDate());
        registrationMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public List<Registration> selectAll() {
        QueryWrapper<Registration> qw = new QueryWrapper<>();
        qw.eq(Constants.STATUS, Constants.STATUS_TRUE);
        return registrationMapper.selectList(qw);
    }

    @Override
    public int insert(RegistrationDto registrationDto) {
        Registration registration = new Registration();
        BeanUtil.copyProperties(registrationDto, registration);
        return registrationMapper.insert(registration);
    }

    @Override
    public int update(RegistrationDto registrationDto) {
        Registration registration = new Registration();
        BeanUtil.copyProperties(registrationDto, registration);
        return registrationMapper.updateById(registration);
    }

    @Override
    public int update(Registration registration) {
        return registrationMapper.updateById(registration);
    }

    @Override
    public int deleteByIds(Long[] registrationIds) {
        List<Long> ids = Arrays.asList(registrationIds);
        if(ids != null && ids.size() > 0){
            return registrationMapper.deleteBatchIds(ids);
        }
        return -1;
    }

    @Override
    public Registration getOneById(String id) {
        return registrationMapper.selectById(id);
    }

    @Override
    public List<Registration> findByAttrList(String attr, Object attrValue) {
        QueryWrapper<Registration> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return registrationMapper.selectList(qw);
    }

    @Override
    public Registration getOneByAttr(String attr, Object attrValue) {
        QueryWrapper<Registration> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return registrationMapper.selectOne(qw);
    }

    @Override
    public int doInvalid(String regId) {
        Registration registration = getOneById(regId);
        if (null != registration) {
            registration.setRegistrationStatus(Constants.REG_STATUS_5);
            return update(registration);
        }
        return -1;
    }

    @Override
    public int doReturn(String regId) {
        Registration registration = getOneById(regId);
        if (null != registration) {
            registration.setRegistrationStatus(Constants.REG_STATUS_4);
            return update(registration);
        }
        return -1;
    }

}