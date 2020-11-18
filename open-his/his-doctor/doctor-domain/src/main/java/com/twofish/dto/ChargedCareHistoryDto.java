package com.twofish.dto;

import com.twofish.domain.CareHistory;
import com.twofish.domain.CareOrder;
import com.twofish.vo.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author ww
 * @description 药用处方表
 * @create 2020-11-17 16:35:08
 */
@ApiModel(value="com-twofish-dto-ChargedCareHistoryDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ChargedCareHistoryDto extends BaseDto {

	private List<CareOrder> careOrders;
	private CareHistory careHistory;

}