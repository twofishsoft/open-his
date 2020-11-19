package com.twofish.dto;

import java.util.Date;

import com.twofish.vo.BaseDto;
import com.twofish.vo.TableDataDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

/**
 * @author ww
 * @description 排班信息表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-SchedulingDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDto extends BaseDto {

	/**
	 * 医生ID
	 */
	@ApiModelProperty(value="医生ID")
	private Integer userId;

	/**
	 * 科室ID
	 */
	@ApiModelProperty(value="科室ID")
	private Integer deptId;

	/**
	 * 值班日期
	 */
	@ApiModelProperty(value="值班日期")
	private String schedulingDay;

	/**
	 * 排班时段1上午  2下午 3晚上 字典表数据翻译
	 */
	@ApiModelProperty(value="排班时段1上午  2下午 3晚上 字典表数据翻译")
	private String subsectionType;

	/**
	 * 排班类型1 门诊 2 急诊 字典表数据翻译
	 */
	@ApiModelProperty(value="排班类型1 门诊 2 急诊 字典表数据翻译")
	private String schedulingType;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Date createTime;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value="创建者")
	private String createBy;

	private String beginDate;
	private List<TableDataDto> data;
}