package com.twofish.controller.system;

import com.twofish.service.DeptService;
import com.twofish.vo.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * 部门数据接口
 * @author ww
 */
@RestController
@Log4j2
@Api(value = "部门数据接口",tags = "部门数据接口")
@RequestMapping("/system/dept/")
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 查询所有的部门数据
     * @return
     */
    @GetMapping("allDept")
    @ApiOperation(value = "查询所有的部门数据", notes = "查询所有的部门数据")
    public AjaxResult allDept(){
        return AjaxResult.success(this.deptService.queryAllDept());
    }

}
