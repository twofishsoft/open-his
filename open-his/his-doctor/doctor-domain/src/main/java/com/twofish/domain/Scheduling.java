package com.twofish.domain;

import java.util.Date;

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
 * @description 排班信息表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-domain-Scheduling")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_scheduling")
public class Scheduling extends BaseEntity {

	/**
	 * 医生ID
	 */
	@ApiModelProperty(value="医生ID")
	@TableId(value = "user_id", type = IdType.AUTO)
	private Integer userId;

	/**
	 * 科室ID
	 */
	@ApiModelProperty(value="科室ID")
	@TableField(value = "dept_id")
	private Integer deptId;

	/**
	 * 值班日期
	 */
	@ApiModelProperty(value="值班日期")
	@TableField(value = "scheduling_day")
	private String schedulingDay;

	/**
	 * 排班时段1上午  2下午 3晚上 字典表数据翻译
	 */
	@ApiModelProperty(value="排班时段1上午  2下午 3晚上 字典表数据翻译")
	@TableField(value = "subsection_type")
	private String subsectionType;

	/**
	 * 排班类型1 门诊 2 急诊 字典表数据翻译
	 */
	@ApiModelProperty(value="排班类型1 门诊 2 急诊 字典表数据翻译")
	@TableField(value = "scheduling_type")
	private String schedulingType;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	@TableField(value = "create_time")
	private Date createTime;

	/**
	 * 创建者
	 */
	@ApiModelProperty(value="创建者")
	@TableField(value = "create_by")
	private String createBy;

	public static final String COL_USER_ID = "user_id";
	public static final String COL_DEPT_ID = "dept_id";
	public static final String COL_SCHEDULING_DAY = "scheduling_day";
	public static final String COL_SUBSECTION_TYPE = "subsection_type";
	public static final String COL_SCHEDULING_TYPE = "scheduling_type";
	public static final String COL_CREATE_TIME = "create_time";
	public static final String COL_CREATE_BY = "create_by";

}