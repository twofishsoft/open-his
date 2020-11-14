package com.twofish.service;

import com.twofish.domain.DictType;
import com.twofish.domain.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.twofish.dto.DicTypeDto;
import com.twofish.dto.NoticeDto;
import com.twofish.vo.DataGridView;

/**
 * @author  Ytyy.
 * @date  2020/11/14 22:25
 * @version 1.0
 */

public interface NoticeService {
    /**
     * 分页多条件查询通知公告
     * @param noticeDto
     * @return
     */
    DataGridView listPage(NoticeDto noticeDto);


    /**
     * 新增通知公告
     * @param noticeDto
     * @return
     */
    Integer insertNotice(NoticeDto noticeDto);

    /**
     * 查询单个通知公告
     * @param noticeId
     * @return
     */
    Notice selectNoticeById(Long noticeId);

    /**
     * 修改字典
     * @param noticeDto
     * @return
     */
    Integer updateNotice(NoticeDto noticeDto);

    /**
     * 删除字典(可以批量删除)
     * @param noticeIds
     * @return
     */
    Integer deleteNoticeByIds(Long[] noticeIds);


}
