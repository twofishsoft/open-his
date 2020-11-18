package com.twofish.dto;

import com.twofish.domain.CareHistory;
import com.twofish.domain.CareOrder;
import com.twofish.domain.CareOrderItem;
import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ww
 * @description 检查的项目详情
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-CheckItemDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CheckItemDto extends BaseDto {

	private CareOrderItem item;
	private CareOrder careOrder;
	private CareHistory careHistory;
}