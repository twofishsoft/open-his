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
 * @description 患者档案
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-PatientFileDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class PatientFileDto extends BaseDto {

	/**
	 * 患者id
	 */
	@ApiModelProperty(value="患者id")
	private String patientId;

	/**
	 * 紧急联系人
	 */
	@ApiModelProperty(value="紧急联系人")
	private String emergencyContactName;

	/**
	 * 紧急联系人电话
	 */
	@ApiModelProperty(value="紧急联系人电话")
	private String emergencyContactPhone;

	/**
	 * 爸爸,妈妈,儿子,女儿,亲戚,朋友
	 */
	@ApiModelProperty(value="爸爸,妈妈,儿子,女儿,亲戚,朋友")
	private String emergencyContactRelation;

	/**
	 * 左耳听力 正常  耳聋
	 */
	@ApiModelProperty(value="左耳听力 正常  耳聋")
	private String leftEarHearing;

	/**
	 * 右耳听力 正常  耳聋
	 */
	@ApiModelProperty(value="右耳听力 正常  耳聋")
	private String rightEarHearing;

	/**
	 * 左眼视力
	 */
	@ApiModelProperty(value="左眼视力")
	private BigDecimal leftVision;

	/**
	 * 右眼视力
	 */
	@ApiModelProperty(value="右眼视力")
	private BigDecimal rightVision;

	/**
	 * 身高
	 */
	@ApiModelProperty(value="身高")
	private BigDecimal height;

	/**
	 * 体重
	 */
	@ApiModelProperty(value="体重")
	private BigDecimal weight;

	/**
	 * 血型 A  B  AB  O    R-阴 RH-阳
	 */
	@ApiModelProperty(value="血型 A  B  AB  O    R-阴 RH-阳")
	private String bloodType;

	/**
	 * 个人史
	 */
	@ApiModelProperty(value="个人史")
	private String personalInfo;

	/**
	 * 家族史
	 */
	@ApiModelProperty(value="家族史")
	private String familyInfo;

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