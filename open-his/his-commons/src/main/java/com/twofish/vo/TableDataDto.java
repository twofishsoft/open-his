package com.twofish.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ww
 * @description
 * @create 2020/11/19 16:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDataDto {

    /**
     * 医生ID
     */
    private Integer userId;

    /**
     * 科室ID
     */
    private Integer deptId;

    /**
     * 排班时段1上午  2下午 3晚上 字典表数据翻译
     */
    private String subsectionType;

    /**
     * 排班类型1 门诊 2 急诊 字典表数据翻译
     */
    private String[] schedulingType;
}
