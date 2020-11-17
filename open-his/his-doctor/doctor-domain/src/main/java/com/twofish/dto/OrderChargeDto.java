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
 * @description 收费表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-OrderChargeDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OrderChargeDto extends BaseDto {

	/**
	 * 收费ID
	 */
	@ApiModelProperty(value="收费ID")
	private String orderId;

	/**
	 * 总费用
	 */
	@ApiModelProperty(value="总费用")
	private BigDecimal orderAmount;

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
	 * 订单状态0未支付  1 支付成功  2支付超时 3支付失败 字典表his_order_charge_status
	 */
	@ApiModelProperty(value="订单状态0未支付  1 支付成功  2支付超时 3支付失败 字典表his_order_charge_status")
	private String orderStatus;

	/**
	 * 支付ID
	 */
	@ApiModelProperty(value="支付ID")
	private String payPlatformId;

	/**
	 * 支付时间
	 */
	@ApiModelProperty(value="支付时间")
	private Date payTime;

	/**
	 * 支付类型0现金1支付宝  字典表	his_pay_type_status
	 */
	@ApiModelProperty(value="支付类型0现金1支付宝  字典表	his_pay_type_status")
	private String payType;

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