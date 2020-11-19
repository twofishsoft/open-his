package com.twofish.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ww
 * @description
 * @create 2020/11/19 16:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchedulingDataDto {

    private String endTimeThisWeek;
    private String startTimeThisWeek;

}
