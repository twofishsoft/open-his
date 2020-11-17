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
 * @description 检查结果
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-domain-CheckResult")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_check_result")
public class CheckResult extends BaseEntity {

	/**
	 * 处方检查项ID
	 */
	@ApiModelProperty(value="处方检查项ID")
	@TableId(value = "coc_id", type = IdType.AUTO)
	private String cocId;

	/**
	 * 检查项目ID
	 */
	@ApiModelProperty(value="检查项目ID")
	@TableField(value = "check_item_id")
	private Integer checkItemId;

	/**
	 * 检查项目名称
	 */
	@ApiModelProperty(value="检查项目名称")
	@TableField(value = "check_item_name")
	private String checkItemName;

	/**
	 * 价格
	 */
	@ApiModelProperty(value="价格")
	@TableField(value = "price")
	private BigDecimal price;

	/**
	 * 检查结果描述
	 */
	@ApiModelProperty(value="检查结果描述")
	@TableField(value = "result_msg")
	private String resultMsg;

	/**
	 * 检查结果扫描附件[json表示]
	 */
	@ApiModelProperty(value="检查结果扫描附件[json表示]")
	@TableField(value = "result_img")
	private String resultImg;

	/**
	 * 患者ID
	 */
	@ApiModelProperty(value="患者ID")
	@TableField(value = "patient_id")
	private String patientId;

	/**
	 * 患者姓名
	 */
	@ApiModelProperty(value="患者姓名")
	@TableField(value = "patient_name")
	private String patientName;

	/**
	 * 是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status
	 */
	@ApiModelProperty(value="是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status")
	@TableField(value = "result_status")
	private String resultStatus;

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

	public static final String COL_COC_ID = "coc_id";
	public static final String COL_CHECK_ITEM_ID = "check_item_id";
	public static final String COL_CHECK_ITEM_NAME = "check_item_name";
	public static final String COL_PRICE = "price";
	public static final String COL_RESULT_MSG = "result_msg";
	public static final String COL_RESULT_IMG = "result_img";
	public static final String COL_PATIENT_ID = "patient_id";
	public static final String COL_PATIENT_NAME = "patient_name";
	public static final String COL_RESULT_STATUS = "result_status";
	public static final String COL_CREATE_TIME = "create_time";
	public static final String COL_UPDATE_TIME = "update_time";
	public static final String COL_CREATE_BY = "create_by";
	public static final String COL_UPDATE_BY = "update_by";

}