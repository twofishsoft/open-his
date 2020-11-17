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
 * @description 开诊细表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-CareOrderItemDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CareOrderItemDto extends BaseDto {

	/**
	 * 开诊明细ID
	 */
	@ApiModelProperty(value="开诊明细ID")
	private String itemId;

	/**
	 * 所属处方id
	 */
	@ApiModelProperty(value="所属处方id")
	private String coId;

	/**
	 * 药品或者检查项目id
	 */
	@ApiModelProperty(value="药品或者检查项目id")
	private String itemRefId;

	/**
	 * 药品或检查项目名称
	 */
	@ApiModelProperty(value="药品或检查项目名称")
	private String itemName;

	/**
	 * 项目类型0药品  还是1检查项
	 */
	@ApiModelProperty(value="项目类型0药品  还是1检查项")
	private String itemType;

	/**
	 * 数量
	 */
	@ApiModelProperty(value="数量")
	private BigDecimal num;

	/**
	 * 单价
	 */
	@ApiModelProperty(value="单价")
	private BigDecimal price;

	/**
	 * 金额
	 */
	@ApiModelProperty(value="金额")
	private BigDecimal amount;

	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remark;

	/**
	 * 状态，0未支付，1已支付，2，已退费  3，已完成 字典表his_order_details_status
	 */
	@ApiModelProperty(value="状态，0未支付，1已支付，2，已退费  3，已完成 字典表his_order_details_status")
	private String status;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

}