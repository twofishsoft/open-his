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
 * @description 患者信息表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-PatientDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto extends BaseDto {

	/**
	 * 主键
	 */
	@ApiModelProperty(value="主键")
	private String patientId;

	/**
	 * 患者姓名
	 */
	@ApiModelProperty(value="患者姓名")
	private String name;

	/**
	 * 患者电话
	 */
	@ApiModelProperty(value="患者电话")
	private String phone;

	/**
	 * 患者性别0男1女 2未知字典表 sys_user_sex
	 */
	@ApiModelProperty(value="患者性别0男1女 2未知字典表 sys_user_sex")
	private String sex;

	/**
	 * 出生年月
	 */
	@ApiModelProperty(value="出生年月")
	private String birthday;

	/**
	 * 身份证号[认证ID]
	 */
	@ApiModelProperty(value="身份证号[认证ID]")
	private String idCard;

	/**
	 * 地址信息
	 */
	@ApiModelProperty(value="地址信息")
	private String address;

	/**
	 * 过敏信息
	 */
	@ApiModelProperty(value="过敏信息")
	private String allergyInfo;

	/**
	 * 是否完善信息，0未完善1已完善 字典表 his_patient_msg_final
	 */
	@ApiModelProperty(value="是否完善信息，0未完善1已完善 字典表 his_patient_msg_final")
	private String isFinal;

	/**
	 * 登录密码
	 */
	@ApiModelProperty(value="登录密码")
	private String password;

	/**
	 * 微信openid
	 */
	@ApiModelProperty(value="微信openid")
	private String openid;

	/**
	 * 最后登录ip
	 */
	@ApiModelProperty(value="最后登录ip")
	private String lastLoginIp;

	/**
	 * 最后登录时间
	 */
	@ApiModelProperty(value="最后登录时间")
	private Date lastLoginTime;

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

}