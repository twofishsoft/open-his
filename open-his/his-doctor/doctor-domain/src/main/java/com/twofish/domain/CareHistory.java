package com.twofish.domain;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author ww
 * @description 病例表
 * @create 2020-11-17 16:30:29
 */
@ApiModel(value="com-twofish-domain-CareHistory")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_care_history")
public class CareHistory extends BaseEntity {

	/**
	 * 病历ID
	 */
	@ApiModelProperty(value="病历ID")
	@TableId(value = "ch_id", type = IdType.AUTO)
	private String chId;

	/**
	 * 医生id
	 */
	@ApiModelProperty(value="医生id")
	@TableField(value = "user_id")
	private Long userId;

	/**
	 * 医生姓名
	 */
	@ApiModelProperty(value="医生姓名")
	@TableField(value = "user_name")
	private String userName;

	/**
	 * 患者id
	 */
	@ApiModelProperty(value="患者id")
	@TableField(value = "patient_id")
	private String patientId;

	/**
	 * 患者姓名
	 */
	@ApiModelProperty(value="患者姓名")
	@TableField(value = "patient_name")
	private String patientName;

	/**
	 * 科室id
	 */
	@ApiModelProperty(value="科室id")
	@TableField(value = "dept_id")
	private Long deptId;

	/**
	 * 科室名称
	 */
	@ApiModelProperty(value="科室名称")
	@TableField(value = "dept_name")
	private String deptName;

	/**
	 * 接诊类型：0初诊，1复诊  字典表属性his_receive_type
	 */
	@ApiModelProperty(value="接诊类型：0初诊，1复诊  字典表属性his_receive_type")
	@TableField(value = "receive_type")
	private String receiveType;

	/**
	 * 是否传染，0否，1是 字典表属性his_contagious_status
	 */
	@ApiModelProperty(value="是否传染，0否，1是 字典表属性his_contagious_status")
	@TableField(value = "is_contagious")
	private String isContagious;

	/**
	 * 就诊时间
	 */
	@ApiModelProperty(value="就诊时间")
	@TableField(value = "care_time")
	private Date careTime;

	/**
	 * 发病日期
	 */
	@ApiModelProperty(value="发病日期")
	@TableField(value = "case_date")
	private String caseDate;

	/**
	 * 挂号单号
	 */
	@ApiModelProperty(value="挂号单号")
	@TableField(value = "reg_id")
	private String regId;

	/**
	 * 主诉
	 */
	@ApiModelProperty(value="主诉")
	@TableField(value = "case_title")
	private String caseTitle;

	/**
	 * 诊断信息
	 */
	@ApiModelProperty(value="诊断信息")
	@TableField(value = "case_result")
	private String caseResult;

	/**
	 * 医生建议
	 */
	@ApiModelProperty(value="医生建议")
	@TableField(value = "doctor_tips")
	private String doctorTips;

	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	@TableField(value = "remark")
	private String remark;

	@ApiModelProperty(value="药用处方表")
	@TableField(value = "careOrders", exist = false)
	private List<CareOrder> careOrders;

	public static final String COL_CH_ID = "ch_id";
	public static final String COL_USER_ID = "user_id";
	public static final String COL_USER_NAME = "user_name";
	public static final String COL_PATIENT_ID = "patient_id";
	public static final String COL_PATIENT_NAME = "patient_name";
	public static final String COL_DEPT_ID = "dept_id";
	public static final String COL_DEPT_NAME = "dept_name";
	public static final String COL_RECEIVE_TYPE = "receive_type";
	public static final String COL_IS_CONTAGIOUS = "is_contagious";
	public static final String COL_CARE_TIME = "care_time";
	public static final String COL_CASE_DATE = "case_date";
	public static final String COL_REG_ID = "reg_id";
	public static final String COL_CASE_TITLE = "case_title";
	public static final String COL_CASE_RESULT = "case_result";
	public static final String COL_DOCTOR_TIPS = "doctor_tips";
	public static final String COL_REMARK = "remark";

}