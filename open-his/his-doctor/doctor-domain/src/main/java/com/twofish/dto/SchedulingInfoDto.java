package com.twofish.dto;

import com.twofish.vo.BaseDto;
import com.twofish.vo.SchedulingDataDto;
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
@ApiModel(value="com-twofish-dto-SchedulingInfoDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingInfoDto extends BaseDto {

	private List<String> labelNames;
	private SchedulingDataDto schedulingData;
	private List<TableDataDto> tableData;
}