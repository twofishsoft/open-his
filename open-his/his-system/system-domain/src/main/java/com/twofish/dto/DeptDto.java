package com.twofish.dto;

import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author ww
 * @description
 * @create 2020/11/8 20:10
 */
@ApiModel(value="com-twofish-dto-DeptDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto extends BaseDto {

    /**
     * 部门ID
     */
    @ApiModelProperty(value="部门ID")
    private Long deptId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value="部门名称")
    private String deptName;

    /**
     * 挂号编号
     */
    @ApiModelProperty(value="挂号编号")
    private Integer regNumber;

    /**
     * 科室编号
     */
    @ApiModelProperty(value="科室编号")
    private String deptNumber;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value="显示顺序")
    private Integer orderNum;

    /**
     * 负责人
     */
    @ApiModelProperty(value="负责人")
    private String deptLeader;

    /**
     * 联系电话
     */
    @ApiModelProperty(value="联系电话")
    private String leaderPhone;

    /**
     * 部门状态（0正常 1停用）
     */
    @ApiModelProperty(value="部门状态（0正常 1停用）")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value="更新者")
    private String updateBy;
}
