package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.Registration;
import com.twofish.dto.RegistrationDto;
import com.twofish.mapper.RegistrationMapper;
import com.twofish.vo.DataGridView;
import org.springframework.stereotype.Service;
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
    public Registration getOneById(Long id) {
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

}