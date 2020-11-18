package com.twofish.dto;

import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ww
 * @description 退费主表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-OrderBackfeeWithCashDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OrderBackfeeWithCashDto extends BaseDto {

	private OrderBackfeeDto orderBackfeeDto;
	private List<OrderBackfeeItemDto> orderBackfeeItemDtoList;

}