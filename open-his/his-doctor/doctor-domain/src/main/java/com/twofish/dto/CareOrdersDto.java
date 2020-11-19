package com.twofish.dto;

import com.twofish.domain.CareOrder;
import com.twofish.domain.CareOrderItem;
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
 * @create 2020/11/19 20:39
 */
@ApiModel(value="com-twofish-dto-CareOrdersDto")
@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class CareOrdersDto extends BaseDto {

    private List<CareOrderItem> careOrderItems;
    private CareOrder careOrder;
}
