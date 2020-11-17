package com.twofish.dto;

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
 * @description 支付订单详情表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-OrderChargeItemDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OrderChargeItemDto extends BaseDto {

	/**
	 * 详情ID和his_care_order_*表里面的ID一样
	 */
	@ApiModelProperty(value="详情ID和his_care_order_*表里面的ID一样")
	private String itemId;

	/**
	 * 处方ID【备用】
	 */
	@ApiModelProperty(value="处方ID【备用】")
	private String coId;

	/**
	 * 项目名称
	 */
	@ApiModelProperty(value="项目名称")
	private String itemName;

	/**
	 * 项目价格
	 */
	@ApiModelProperty(value="项目价格")
	private BigDecimal itemPrice;

	/**
	 * 项目数量
	 */
	@ApiModelProperty(value="项目数量")
	private Integer itemNum;

	/**
	 * 小计
	 */
	@ApiModelProperty(value="小计")
	private BigDecimal itemAmount;

	/**
	 * 订单IDhis_oder_charge主键
	 */
	@ApiModelProperty(value="订单IDhis_oder_charge主键")
	private String orderId;

	/**
	 * 项目类型0药品  还是1检查项
	 */
	@ApiModelProperty(value="项目类型0药品  还是1检查项")
	private String itemType;

	/**
	 * 状态，0未支付，1已支付，2，已退费  3，已完成 字典表 his_order_details_status
	 */
	@ApiModelProperty(value="状态，0未支付，1已支付，2，已退费  3，已完成 字典表 his_order_details_status")
	private String status;

}