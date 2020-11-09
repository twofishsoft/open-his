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
 * @description 角色数据传输对象
 * @create 2020/11/7 19:17
 */
@ApiModel(value="com-twofish-dto-RoleDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto extends BaseDto {

    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value="角色名称")
    private String roleName;

    /**
     * 角色权限编码
     */
    @ApiModelProperty(value="角色权限编码")
    private String roleCode;

    /**
     * 显示顺序
     */
    @ApiModelProperty(value="显示顺序")
    private Integer roleSort;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

    /**
     * 角色状态（0正常 1停用）
     */
    @ApiModelProperty(value="角色状态（0正常 1停用）")
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

    /**
     * 角色菜单关系
     */
    @ApiModelProperty(value="角色菜单关系")
    private Long[] menuIds;

    /**
     * 多选角色
     */
    @ApiModelProperty(value="多选角色")
    private Long[] roleIds;

}
