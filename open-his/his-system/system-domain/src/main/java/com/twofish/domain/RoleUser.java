package com.twofish.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ww
 * @description 用户角色关系
 * @create 2020/11/7 18:13
 */
@ApiModel(value="com-twofish-domain-RoleUser")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_user")
public class RoleUser extends BaseEntity {

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    @ApiModelProperty(value="角色ID")
    private Long roleId;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_ROLE_ID = "role_id";

}
