package com.twofish.dto;

import java.util.Date;
import java.math.BigDecimal;

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
 * @description 挂号项目
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-RegisteredItemDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredItemDto extends BaseDto {

	/**
	 * 挂号项ID
	 */
	@ApiModelProperty(value="挂号项ID")
	private Long regItemId;

	/**
	 * 挂号项目名称
	 */
	@ApiModelProperty(value="挂号项目名称")
	private String regItemName;

	/**
	 * 金额
	 */
	@ApiModelProperty(value="金额")
	private BigDecimal regItemFee;

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
	 * 状态（0正常 1停用）
	 */
	@ApiModelProperty(value="状态（0正常 1停用）")
	private String status;

	/**
	 * 删除标志（0正常 1删除）
	 */
	@ApiModelProperty(value="删除标志（0正常 1删除）")
	private String delFlag;

}