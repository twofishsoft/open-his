package com.twofish.domain;

import java.util.Date;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.util.Date;

/**
 * @author ww
 * @description 挂号项目
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-domain-RegisteredItem")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_registered_item")
public class RegisteredItem extends BaseEntity {

	/**
	 * 挂号项ID
	 */
	@ApiModelProperty(value="挂号项ID")
	@TableId(value = "reg_item_id", type = IdType.AUTO)
	private Long regItemId;

	/**
	 * 挂号项目名称
	 */
	@ApiModelProperty(value="挂号项目名称")
	@TableField(value = "reg_item_name")
	private String regItemName;

	/**
	 * 金额
	 */
	@ApiModelProperty(value="金额")
	@TableField(value = "reg_item_fee")
	private BigDecimal regItemFee;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@TableField(value = "create_time")
	private Date createTime;

	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间")
	@TableField(value = "update_time")
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

	/**
	 * 状态（0正常 1停用）
	 */
	@ApiModelProperty(value="状态（0正常 1停用）")
	@TableField(value = "status")
	private String status;

	/**
	 * 删除标志（0正常 1删除）
	 */
	@ApiModelProperty(value="删除标志（0正常 1删除）")
	@TableField(value = "del_flag")
	private String delFlag;

	public static final String COL_REG_ITEM_ID = "reg_item_id";
	public static final String COL_REG_ITEM_NAME = "reg_item_name";
	public static final String COL_REG_ITEM_FEE = "reg_item_fee";
	public static final String COL_CREATE_TIME = "create_time";
	public static final String COL_UPDATE_TIME = "update_time";
	public static final String COL_CREATE_BY = "create_by";
	public static final String COL_UPDATE_BY = "update_by";
	public static final String COL_STATUS = "status";
	public static final String COL_DEL_FLAG = "del_flag";

}