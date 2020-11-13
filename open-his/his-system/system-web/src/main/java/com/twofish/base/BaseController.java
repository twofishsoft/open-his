package com.twofish.base;

import com.twofish.annotation.CurrUser;
import com.twofish.vo.AjaxResult;
import com.twofish.vo.BaseDto;
import com.twofish.vo.DataGridView;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author ww
 * @description
 * @create 2020/11/13 15:50
 */
public abstract class BaseController<T extends BaseDto> {

    public abstract BaseService getService();

    /**
     * 分页查询
     * @param baseDto
     * @return
     */
    @GetMapping("listForPage")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    public AjaxResult listForPage(T baseDto){
        DataGridView dataGridView = this.getService().listPage(baseDto);
        return AjaxResult.success("查询成功", dataGridView.getData(), dataGridView.getTotal());
    }

    /**
     * 新增
     * @param baseDto
     * @return
     */
    @PostMapping("insert")
    @ApiOperation(value = "新增", notes = "新增")
    public AjaxResult insert(@CurrUser T baseDto){
        return AjaxResult.toAjax(this.getService().insert(baseDto));
    }

    /**
     * 修改
     * @param baseDto
     * @return
     */
    @PutMapping("update")
    @ApiOperation(value = "修改", notes = "修改")
    public AjaxResult update(@CurrUser(name = "updateBy") T baseDto){
        return AjaxResult.toAjax(this.getService().update(baseDto));
    }

    /**
     * 删除(可批量删除)
     * @param ids
     * @return
     */
    @DeleteMapping("deleteByIds/{ids}")
    @ApiOperation(value = "删除(可批量删除)", notes = "删除(可批量删除)")
    public AjaxResult deleteByIds(@PathVariable Long[] ids){
        return AjaxResult.toAjax(this.getService().deleteByIds(ids));
    }

    /**
     * 查询所有可用信息
     * @return
     */
    @GetMapping("selectAll")
    @ApiOperation(value = "查询所有可用信息", notes = "查询所有可用信息")
    public AjaxResult selectAll() {
        return AjaxResult.success(this.getService().selectAll());
    }

    /**
     * 根据ID查询数据
     * @param id
     * @return
     */
    @GetMapping("getById/{id}")
    @ApiOperation(value = "根据ID查询数据", notes = "根据ID查询数据")
    public AjaxResult getOne(@PathVariable Long id){
        return AjaxResult.success("查询成功", this.getService().getOneById(id));
    }

}
