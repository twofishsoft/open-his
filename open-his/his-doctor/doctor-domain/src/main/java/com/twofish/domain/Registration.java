package com.twofish.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 挂号信息表
 * @author ww
 * @date 2020-11-17 13:39:37
 **/
@ApiModel(value="com-twofish-domain-Registration")
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "his_registration")
public class Registration extends BaseEntity {

    /**
     * 挂号流水
     */
    @ApiModelProperty(value="挂号流水")
    @TableId(value = "registration_id", type = IdType.AUTO)
    private String registrationId;

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
     * 接诊医生ID
     */
    @ApiModelProperty(value="接诊医生ID")
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 接诊医生姓名
     */
    @ApiModelProperty(value="接诊医生姓名")
    @TableField(value = "doctor_name")
    private String doctorName;

    /**
     * 科室ID
     */
    @ApiModelProperty(value="科室ID")
    @TableField(value = "dept_id")
    private Long deptId;

    /**
     * 挂号费用ID
     */
    @ApiModelProperty(value="挂号费用ID")
    @TableField(value = "registration_item_id")
    private Long registrationItemId;

    /**
     * 挂号总金额
     */
    @ApiModelProperty(value="挂号总金额")
    @TableField(value = "registration_amount")
    private BigDecimal registrationAmount;

    /**
     * 挂号编号
     */
    @ApiModelProperty(value="挂号编号")
    @TableField(value = "registration_number")
    private Integer registrationNumber;

    /**
     * 挂号状态0未收费,1待就诊，2,就诊中，3，就诊完成，4，已退号，5 作废
     */
    @ApiModelProperty(value="挂号状态0未收费,1待就诊，2,就诊中，3，就诊完成，4，已退号，5 作废")
    @TableField(value = "registration_status")
    private String registrationStatus;

    /**
     * 就诊日期
     */
    @ApiModelProperty(value="就诊日期")
    @TableField(value = "visit_date")
    private String visitDate;

    /**
     * 排班类型1 门诊 2 急诊 字典表数据翻译
     */
    @ApiModelProperty(value="排班类型1 门诊 2 急诊 字典表数据翻译")
    @TableField(value = "scheduling_type")
    private String schedulingType;

    /**
     * 排班时段1上午  2下午 3晚上 字典表数据翻译
     */
    @ApiModelProperty(value="排班时段1上午  2下午 3晚上 字典表数据翻译")
    @TableField(value = "subsection_type")
    private String subsectionType;

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
     * 创建人
     */
    @ApiModelProperty(value="创建人")
    @TableField(value = "create_by")
    private String createBy;

    public static final String COL_REGISTRATION_ID = "registration_id";
    public static final String COL_PATIENT_ID = "patient_id";
    public static final String COL_PATIENT_NAME = "patient_name";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_DOCTOR_NAME = "doctor_name";
    public static final String COL_DEPT_ID = "dept_id";
    public static final String COL_REGISTRATION_ITEM_ID = "registration_item_id";
    public static final String COL_REGISTRATION_AMOUNT = "registration_amount";
    public static final String COL_REGISTRATION_NUMBER = "registration_number";
    public static final String COL_REGISTRATION_STATUS = "registration_status";
    public static final String COL_VISIT_DATE = "visit_date";
    public static final String COL_SCHEDULING_TYPE = "scheduling_type";
    public static final String COL_SUBSECTION_TYPE = "subsection_type";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_UPDATE_TIME = "update_time";
    public static final String COL_CREATE_BY = "create_by";
}
