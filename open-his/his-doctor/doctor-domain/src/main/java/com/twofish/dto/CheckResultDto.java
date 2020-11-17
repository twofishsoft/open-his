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
 * @description 检查结果
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-CheckResultDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CheckResultDto extends BaseDto {

	/**
	 * 处方检查项ID
	 */
	@ApiModelProperty(value="处方检查项ID")
	private String cocId;

	/**
	 * 检查项目ID
	 */
	@ApiModelProperty(value="检查项目ID")
	private Integer checkItemId;

	/**
	 * 检查项目名称
	 */
	@ApiModelProperty(value="检查项目名称")
	private String checkItemName;

	/**
	 * 价格
	 */
	@ApiModelProperty(value="价格")
	private BigDecimal price;

	/**
	 * 检查结果描述
	 */
	@ApiModelProperty(value="检查结果描述")
	private String resultMsg;

	/**
	 * 检查结果扫描附件[json表示]
	 */
	@ApiModelProperty(value="检查结果扫描附件[json表示]")
	private String resultImg;

	/**
	 * 患者ID
	 */
	@ApiModelProperty(value="患者ID")
	private String patientId;

	/**
	 * 患者姓名
	 */
	@ApiModelProperty(value="患者姓名")
	private String patientName;

	/**
	 * 是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status
	 */
	@ApiModelProperty(value="是否录入检查结果0 检测中  1 检测完成  字典表 his_check_result_status")
	private String resultStatus;

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