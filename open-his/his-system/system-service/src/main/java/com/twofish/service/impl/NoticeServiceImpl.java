package com.twofish.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twofish.domain.Notice;
import com.twofish.dto.NoticeDto;
import com.twofish.mapper.NoticeMapper;
import com.twofish.service.NoticeService;
import com.twofish.vo.DataGridView;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ytyy.
 * @version 1.0
 * @date 2020/11/14 22:25
 */

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public DataGridView listPage(NoticeDto noticeDto) {
        Page<Notice> page = new Page<>(noticeDto.getPageNum(),noticeDto.getPageSize());
        QueryWrapper<Notice> wrapper = new QueryWrapper<>();

        wrapper.eq(StringUtils.isNotBlank(noticeDto.getNoticeTitle()), Notice.COL_NOTICE_TITLE, noticeDto.getNoticeTitle());
        wrapper.eq(StringUtils.isNotBlank(noticeDto.getNoticeType()), Notice.COL_NOTICE_TYPE, noticeDto.getNoticeType());
        wrapper.eq(StringUtils.isNotBlank(noticeDto.getCreateBy()), Notice.COL_CREATE_BY, noticeDto.getCreateBy());
        wrapper.ge(null != noticeDto.getBeginTime(), Notice.COL_CREATE_TIME, noticeDto.getBeginTime());
        wrapper.le(null != noticeDto.getEndTime(), Notice.COL_CREATE_TIME, noticeDto.getEndTime());
        this.noticeMapper.selectPage(page, wrapper);

        return new DataGridView(page.getTotal(), page.getRecords());
    }

    @Override
    public Integer insertNotice(NoticeDto noticeDto) {
        Notice notice = new Notice();
        BeanUtil.copyProperties(noticeDto, notice);
        // 设置创建人及时间
        notice.setCreateBy(noticeDto.getSimpleUser().getUserName());
        notice.setCreateTime(noticeDto.getCreateTime());
        // 设置第一次创建时默认的修改
        notice.setUpdateBy(noticeDto.getSimpleUser().getUserName());
        notice.setUpdateTime(noticeDto.getCreateTime());

        return this.noticeMapper.insert(notice);
    }

    @Override
    public Notice selectNoticeById(Long noticeId) {
        return this.noticeMapper.selectById(noticeId);
    }

    @Override
    public Integer updateNotice(NoticeDto noticeDto) {
        Notice notice = new Notice();
        BeanUtil.copyProperties(noticeDto, notice);
        // 设置修改人和时间
        notice.setUpdateBy(noticeDto.getSimpleUser().getUserName());
        notice.setUpdateTime(DateUtil.date());

        return this.noticeMapper.updateById(notice);
    }

    @Override
    public Integer deleteNoticeByIds(Long[] noticeIds) {
        List<Long> ids = Arrays.asList(noticeIds);
        if (ids.size() > 0) {
            return this.noticeMapper.deleteBatchIds(ids);
        }
        return -1;
    }
}
