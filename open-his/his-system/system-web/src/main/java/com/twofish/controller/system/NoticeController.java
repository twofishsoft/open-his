package com.twofish.controller.system;

import com.twofish.domain.Notice;
import com.twofish.dto.NoticeDto;
import com.twofish.service.NoticeService;
import com.twofish.utils.ShiroSecurityUtils;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Ytyy.
 * @version 1.0
 * @date 2020/11/14 22:52
 */
@RestController
@Log4j2
@Api(value = "系统通知公告接口", tags = "通知公告接口")
@RequestMapping(value = "/system/notice/")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @GetMapping(value = "listNoticeForPage")
    @ApiOperation(value = "分页查询通知公告", notes = "通知公告分页")
    public AjaxResult listNoticeForPage(NoticeDto noticeDto) {
        DataGridView gridView = noticeService.listPage(noticeDto);
        if (gridView.getData().size() <= 0) {
            return AjaxResult.fail("查询数据不存在");
        }
        return AjaxResult.success("查询成功", gridView.getData(), gridView.getTotal());
    }

    @PostMapping(value = "addNotice")
    @ApiOperation(value = "添加公告通知", notes = "添加公告通知")
    public AjaxResult addNotice(@Validated NoticeDto noticeDto) {
        //设置添加人
        noticeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.noticeService.insertNotice(noticeDto));
    }

    @GetMapping(value = "getNoticeById/{noticeId}")
    @ApiOperation(value = "根据id查询单个公告通知", notes = "查询单个公告通知")
    public AjaxResult getNoticeById(@PathVariable @Validated @NotNull Long noticeId) {
        Notice notice = this.noticeService.selectNoticeById(noticeId);
        if (notice != null) {
            return AjaxResult.success("查询成功", notice);
        }
        return AjaxResult.fail("查询的数据不存在");
    }

    @PutMapping(value = "updateNotice")
    @ApiOperation(value = "修改公告通知", notes = "修改公告通知")
    public AjaxResult updateNotice(@Validated NoticeDto noticeDto) {
        //设置修改人
        noticeDto.setSimpleUser(ShiroSecurityUtils.getCurrentSimpleUser());
        return AjaxResult.toAjax(this.noticeService.updateNotice(noticeDto));
    }

    @DeleteMapping(value = "deleteNoticeByIds/{noticeId}")
    @ApiOperation(value = "根据id批量删除公告通知", notes = "批量删除公告通知")
    public AjaxResult deleteNoticeByIds(@PathVariable @Validated @NotEmpty Long[] noticeId) {
        return AjaxResult.toAjax(this.noticeService.deleteNoticeByIds(noticeId));
    }


}
