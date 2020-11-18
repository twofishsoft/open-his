package com.twofish.dto;

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
 * @create 2020/11/18 17:23
 */
@ApiModel(value="com-twofish-dto-OrderChargeWithCashDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class OrderChargeWithCashDto extends BaseDto {

    private OrderChargeDto orderChargeDto;
    private List<OrderChargeItemDto> orderChargeItemDto;
}
