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
 * @description 退费主表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-OrderBackfeeDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OrderBackfeeDto extends BaseDto {

	/**
	 * 退费ID
	 */
	@ApiModelProperty(value="退费ID")
	private String backId;

	/**
	 * 总费用
	 */
	@ApiModelProperty(value="总费用")
	private BigDecimal backAmount;

	/**
	 * 病历ID
	 */
	@ApiModelProperty(value="病历ID")
	private String chId;

	/**
	 * 挂号单
	 */
	@ApiModelProperty(value="挂号单")
	private String regId;

	/**
	 * 患者名称
	 */
	@ApiModelProperty(value="患者名称")
	private String patientName;

	/**
	 * 订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status
	 */
	@ApiModelProperty(value="订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status")
	private String backStatus;

	/**
	 * 退费类型0现金1支付宝  字典表his_pay_type_status
	 */
	@ApiModelProperty(value="退费类型0现金1支付宝  字典表his_pay_type_status")
	private String backType;

	/**
	 * 关联订单ID his_order_charge  
	 */
	@ApiModelProperty(value="关联订单ID his_order_charge  ")
	private String orderId;

	/**
	 * 退费交易ID
	 */
	@ApiModelProperty(value="退费交易ID")
	private String backPlatformId;

	/**
	 * 退费交易时间
	 */
	@ApiModelProperty(value="退费交易时间")
	private Date backTime;

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