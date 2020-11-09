package com.twofish.dto;

import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ww
 * @description 用户角色类型传输对象
 * @create 2020/11/7 18:21
 */
@ApiModel(value="com-twofish-dto-RoleUserDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDto extends BaseDto {

    /**
     * 用户ID
     */
    @ApiModelProperty(value="用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value="角色ID")
    private Long roleId;

}
