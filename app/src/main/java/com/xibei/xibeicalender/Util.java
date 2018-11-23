package com.xibei.xibeicalender;

import java.util.Calendar;

public class Util {

    //判断该月第一天是周几
    public static int getMonthFirstDay(int afterMonth) {
        Calendar resultCalender;
        Calendar calendar  = Calendar.getInstance();
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+afterMonth);
        resultCalender = (Calendar) calendar.clone();
        resultCalender.set(Calendar.DAY_OF_MONTH, 0);
        return resultCalender.get(Calendar.DAY_OF_WEEK);
    }

    //判断当前月最后一天周几
    public static int getMonthLastDay(int afterMonth) {
        Calendar resultCalender;
        Calendar calendar  = Calendar.getInstance();
        calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+afterMonth);
        resultCalender = (Calendar) calendar.clone();
        resultCalender.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        return resultCalender.get(Calendar.DAY_OF_WEEK);
    }

}
