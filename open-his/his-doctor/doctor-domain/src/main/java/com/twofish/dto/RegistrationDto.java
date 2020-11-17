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

/**
 * @author ww
 * @description 挂号信息表
 * @create 2020-11-17 13:58:14
 */
@ApiModel(value="com-twofish-dto-RegistrationDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto extends BaseDto {

	/**
	 * 挂号流水
	 */
	@ApiModelProperty(value="挂号流水")
	private String registrationId;

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
	 * 接诊医生ID
	 */
	@ApiModelProperty(value="接诊医生ID")
	private Long userId;

	/**
	 * 接诊医生姓名
	 */
	@ApiModelProperty(value="接诊医生姓名")
	private String doctorName;

	/**
	 * 科室ID
	 */
	@ApiModelProperty(value="科室ID")
	private Long deptId;

	/**
	 * 挂号费用ID
	 */
	@ApiModelProperty(value="挂号费用ID")
	private Long registrationItemId;

	/**
	 * 挂号总金额
	 */
	@ApiModelProperty(value="挂号总金额")
	private BigDecimal registrationAmount;

	/**
	 * 挂号编号
	 */
	@ApiModelProperty(value="挂号编号")
	private Integer registrationNumber;

	/**
	 * 挂号状态0未收费,1待就诊，2,就诊中，3，就诊完成，4，已退号，5 作废
	 */
	@ApiModelProperty(value="挂号状态0未收费,1待就诊，2,就诊中，3，就诊完成，4，已退号，5 作废")
	private String registrationStatus;

	/**
	 * 就诊日期
	 */
	@ApiModelProperty(value="就诊日期")
	private String visitDate;

	/**
	 * 排班类型1 门诊 2 急诊 字典表数据翻译
	 */
	@ApiModelProperty(value="排班类型1 门诊 2 急诊 字典表数据翻译")
	private String schedulingType;

	/**
	 * 排班时段1上午  2下午 3晚上 字典表数据翻译
	 */
	@ApiModelProperty(value="排班时段1上午  2下午 3晚上 字典表数据翻译")
	private String subsectionType;

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
	 * 创建人
	 */
	@ApiModelProperty(value="创建人")
	private String createBy;

}