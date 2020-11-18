package com.twofish.domain;

import com.baomidou.mybatisplus.annotation.*;
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
 * @create 2020/11/7 19:08
 */
@ApiModel(value="com-twofish-domain-Role")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class Role extends BaseEntity {

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
     * 角色权限编码
     */
    @TableField(value = "role_code")
    @ApiModelProperty(value="角色权限编码")
    private String roleCode;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    @ApiModelProperty(value="显示顺序")
    private Integer roleSort;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "status")
    @ApiModelProperty(value="角色状态（0正常 1停用）")
    private String status;

    @TableField(exist = false)
    @ApiModelProperty(value="状态名称")
    private String statusName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty(value="创建者")
    private String createBy;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty(value="更新者")
    private String updateBy;

    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_ROLE_Name = "role_name";
    public static final String COL_ROLE_CODE = "role_code";
    public static final String COL_ROLE_SORT = "role_sort";
    public static final String COL_REMARK = "remark";
    public static final String COL_STATUS = "status";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_CREATE_BY = "create_by";
    public static final String COL_UPDATE_BY = "update_by";
}