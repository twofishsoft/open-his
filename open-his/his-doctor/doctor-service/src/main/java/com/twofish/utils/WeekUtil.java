package com.twofish.utils;

import com.twofish.vo.SchedulingDataDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/19 16:40
 */
public class WeekUtil {

    /**
     * 根据日期获取当天是周几
     * @param datetime 日期
     * @return 周几
     */
    public static String dateToWeek(String datetime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = sdf.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDays[w];
    }

    /**
     * 大于:1,小于:-1 等于:0
     */
    public static Integer compareTime(String start, String end) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//这个地方时间规格可根据自己的需求修改
        long result = sdf.parse(start).getTime() - sdf.parse(end).getTime();
        return result < 0L?Integer.valueOf(-1):(result == 0L?Integer.valueOf(0):Integer.valueOf(1));
    }

    /**
     * 获取合适的打卡时间
     */
    public static SchedulingDataDto getClock(List<String> times) {
        if (times.size() == 0) {
            return new SchedulingDataDto();
        }
        String min = times.get(0);
        String max = times.get(0);
        try {
            for (int i = 1; i < times.size(); i++) {
                Integer integer = WeekUtil.compareTime(min, times.get(i));
                if (integer == 1) {
                    min = times.get(i);
                }
                integer = WeekUtil.compareTime(max, times.get(i));
                if (integer == -1) {
                    max = times.get(i);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SchedulingDataDto(max, min);
    }

}
