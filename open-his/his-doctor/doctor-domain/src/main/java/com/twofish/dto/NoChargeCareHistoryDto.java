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
 * @description
 * @create 2020/11/18 16:29
 */
@ApiModel(value="com-twofish-dto-NoChargeCareHistoryDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class NoChargeCareHistoryDto extends BaseDto {

    private List<CareOrder> careOrders;
    private CareHistory careHistory;
}
