package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.constants.Constants;
import com.twofish.domain.CareHistory;
import com.twofish.domain.CareOrder;
import com.twofish.domain.CareOrderItem;
import com.twofish.domain.CheckResult;
import com.twofish.dto.CareOrderItemDto;
import com.twofish.dto.CheckItemDto;
import com.twofish.dto.CheckResultDto;
import com.twofish.mapper.CheckResultMapper;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import twofish.service.CareHistoryService;
import twofish.service.CareOrderItemService;
import twofish.service.CareOrderService;
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
    @Resource
    private CareOrderItemService careOrderItemService;
    @Resource
    private CareOrderService careOrderService;
    @Resource
    private CareHistoryService careHistoryService;

    @Override
    public DataGridView listPage(CheckResultDto checkResultDto) {
        String patientName = checkResultDto.getPatientName();
        Integer[] checkItemIds = checkResultDto.getCheckItemIds();
        Page<CheckResult> page = new Page<>(checkResultDto.getPageNum(), checkResultDto.getPageSize());
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(patientName), CheckResult.COL_PATIENT_NAME, patientName);
        qw.in(checkItemIds.length != 0, CheckResult.COL_CHECK_ITEM_ID, checkItemIds);
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
    public CheckResult findById(String id) {
        return checkResultMapper.selectById(id);
    }

    @Override
    public List<CheckResult> queryByAttrList(String attr, Object attrValue) {
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return checkResultMapper.selectList(qw);
    }

    @Override
    public CheckResult queryOneByAttr(String attr, Object attrValue) {
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.eq(attr, attrValue);
        return checkResultMapper.selectOne(qw);
    }

    @Override
    public int completeCheckResult(CheckResultDto checkResultDto) {
        Integer checkItemId = checkResultDto.getCheckItemId();
        CheckResult checkResult = queryOneByAttr(CheckResult.COL_CHECK_ITEM_ID, checkItemId);
        if (null != checkResult) {
            checkResult.setResultImg(checkResultDto.getResultImg());
            checkResult.setResultMsg(checkResultDto.getResultMsg());
            checkResult.setResultStatus(Constants.RESULT_STATUS_1);
            checkResult.setUpdateBy(checkResultDto.getUpdateBy());
            return update(checkResult);
        }
        return -1;
    }

    @Override
    public int startCheck(String itemId) {
        CheckResult checkResult = queryOneByAttr(CheckResult.COL_CHECK_ITEM_ID, itemId);
        if (null != checkResult) {
            checkResult.setResultStatus(Constants.RESULT_STATUS_0);
            return update(checkResult);
        }
        return -1;
    }

    @Override
    public DataGridView queryAllCheckingResultForPage(CheckResultDto checkResultDto) {
        String patientName = checkResultDto.getPatientName();
        Integer[] checkItemIds = checkResultDto.getCheckItemIds();
        Page<CheckResult> page = new Page<>(checkResultDto.getPageNum(), checkResultDto.getPageSize());
        QueryWrapper<CheckResult> qw = new QueryWrapper<>();
        qw.like(StringUtils.isNotBlank(patientName), CheckResult.COL_PATIENT_NAME, patientName);
        qw.in(checkItemIds.length != 0, CheckResult.COL_CHECK_ITEM_ID, checkItemIds);
        qw.eq( CheckResult.COL_RESULT_STATUS, Constants.RESULT_STATUS_0);
        checkResultMapper.selectPage(page, qw);
        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public CheckItemDto queryCheckItemByItemId(String itemId) {
        CheckItemDto checkItemDto = new CheckItemDto();
        CareOrderItem careOrderItem = careOrderItemService.findById(itemId);
        if (null != careOrderItem) {
            checkItemDto.setItem(careOrderItem);
            CareOrder careOrder = careOrderService.findById(careOrderItem.getCoId());
            if (null != careOrder) {
                checkItemDto.setCareOrder(careOrder);
                CareHistory careHistory = careHistoryService.findById(careOrder.getChId());
                checkItemDto.setCareHistory(careHistory);
            }
        }
        return checkItemDto;
    }

}