package com.twofish.dto;

import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @description 药用处方表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-CareOrderDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CareOrderDto extends BaseDto {

	/**
	 * 处方ID
	 */
	@ApiModelProperty(value="处方ID")
	private String coId;

	/**
	 * 处方类型0药用处方1检查处方
	 */
	@ApiModelProperty(value="处方类型0药用处方1检查处方")
	private String coType;

	/**
	 * 医生id
	 */
	@ApiModelProperty(value="医生id")
	private Long userId;

	/**
	 * 患者id
	 */
	@ApiModelProperty(value="患者id")
	private String patientId;

	/**
	 * 患者姓名
	 */
	@ApiModelProperty(value="患者姓名")
	private String patientName;

	/**
	 * 关联病历id
	 */
	@ApiModelProperty(value="关联病历id")
	private String chId;

	/**
	 * 处方全额
	 */
	@ApiModelProperty(value="处方全额")
	private BigDecimal allAmount;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value="创建者")
	private String createBy;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * 更新者
	 */
	@ApiModelProperty(value="更新者")
	private String updateBy;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	private Date updateTime;

	@ApiModelProperty(value="订单状态0未支付  1 支付成功  2支付超时 3支付失败 字典表his_order_charge_status")
	private String orderStatus;

}