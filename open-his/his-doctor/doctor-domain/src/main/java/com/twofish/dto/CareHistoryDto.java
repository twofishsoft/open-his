package com.twofish.dto;

import java.util.Date;

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
 * @description 病例表
 * @create 2020-11-17 16:30:29
 */
@ApiModel(value="com-twofish-dto-CareHistoryDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CareHistoryDto extends BaseDto {

	/**
	 * 病历ID
	 */
	@ApiModelProperty(value="病历ID")
	private String chId;

	/**
	 * 医生id
	 */
	@ApiModelProperty(value="医生id")
	private Long userId;

	/**
	 * 医生姓名
	 */
	@ApiModelProperty(value="医生姓名")
	private String userName;

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
	 * 科室id
	 */
	@ApiModelProperty(value="科室id")
	private Long deptId;

	/**
	 * 科室名称
	 */
	@ApiModelProperty(value="科室名称")
	private String deptName;

	/**
	 * 接诊类型：0初诊，1复诊  字典表属性his_receive_type
	 */
	@ApiModelProperty(value="接诊类型：0初诊，1复诊  字典表属性his_receive_type")
	private String receiveType;

	/**
	 * 是否传染，0否，1是 字典表属性his_contagious_status
	 */
	@ApiModelProperty(value="是否传染，0否，1是 字典表属性his_contagious_status")
	private String isContagious;

	/**
	 * 就诊时间
	 */
	@ApiModelProperty(value="就诊时间")
	private Date careTime;

	/**
	 * 发病日期
	 */
	@ApiModelProperty(value="发病日期")
	private String caseDate;

	/**
	 * 挂号单号
	 */
	@ApiModelProperty(value="挂号单号")
	private String regId;

	/**
	 * 主诉
	 */
	@ApiModelProperty(value="主诉")
	private String caseTitle;

	/**
	 * 诊断信息
	 */
	@ApiModelProperty(value="诊断信息")
	private String caseResult;

	/**
	 * 医生建议
	 */
	@ApiModelProperty(value="医生建议")
	private String doctorTips;

	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String remark;

}