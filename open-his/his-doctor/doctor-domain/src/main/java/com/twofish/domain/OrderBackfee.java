package com.twofish.domain;

import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.Date;

/**
 * @author ww
 * @description 退费主表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-domain-OrderBackfee")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_order_backfee")
public class OrderBackfee extends BaseEntity {

	/**
	 * 退费ID
	 */
	@ApiModelProperty(value="退费ID")
	@TableId(value = "back_id", type = IdType.AUTO)
	private String backId;

	/**
	 * 总费用
	 */
	@ApiModelProperty(value="总费用")
	@TableField(value = "back_amount")
	private BigDecimal backAmount;

	/**
	 * 病历ID
	 */
	@ApiModelProperty(value="病历ID")
	@TableField(value = "ch_id")
	private String chId;

	/**
	 * 挂号单
	 */
	@ApiModelProperty(value="挂号单")
	@TableField(value = "reg_id")
	private String regId;

	/**
	 * 患者名称
	 */
	@ApiModelProperty(value="患者名称")
	@TableField(value = "patient_name")
	private String patientName;

	/**
	 * 订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status
	 */
	@ApiModelProperty(value="订单状态0未退费  1 退费成功 2退费失败  字典表his_backfee_status")
	@TableField(value = "back_status")
	private String backStatus;

	/**
	 * 退费类型0现金1支付宝  字典表his_pay_type_status
	 */
	@ApiModelProperty(value="退费类型0现金1支付宝  字典表his_pay_type_status")
	@TableField(value = "back_type")
	private String backType;

	/**
	 * 关联订单ID his_order_charge  
	 */
	@ApiModelProperty(value="关联订单ID his_order_charge  ")
	@TableField(value = "order_id")
	private String orderId;

	/**
	 * 退费交易ID
	 */
	@ApiModelProperty(value="退费交易ID")
	@TableField(value = "back_platform_id")
	private String backPlatformId;

	/**
	 * 退费交易时间
	 */
	@ApiModelProperty(value="退费交易时间")
	@TableField(value = "back_time")
	private Date backTime;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@TableField(value = "create_time", fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	@TableField(value = "update_time", fill = FieldFill.UPDATE)
	private Date updateTime;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value="创建者")
	@TableField(value = "create_by")
	private String createBy;

	/**
	 * 更新者
	 */
	@ApiModelProperty(value="更新者")
	@TableField(value = "update_by")
	private String updateBy;

	public static final String COL_BACK_ID = "back_id";
	public static final String COL_BACK_AMOUNT = "back_amount";
	public static final String COL_CH_ID = "ch_id";
	public static final String COL_REG_ID = "reg_id";
	public static final String COL_PATIENT_NAME = "patient_name";
	public static final String COL_BACK_STATUS = "back_status";
	public static final String COL_BACK_TYPE = "back_type";
	public static final String COL_ORDER_ID = "order_id";
	public static final String COL_BACK_PLATFORM_ID = "back_platform_id";
	public static final String COL_BACK_TIME = "back_time";
	public static final String COL_CREATE_TIME = "create_time";
	public static final String COL_UPDATE_TIME = "update_time";
	public static final String COL_CREATE_BY = "create_by";
	public static final String COL_UPDATE_BY = "update_by";

}