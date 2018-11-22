package com.xibei.xibeicalender;

import java.util.Calendar;

public class Util {


    //平年闰年判断
    public static boolean isPingYear(Calendar calendar) {
        calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) % 4 != 0 ? true : false;

    }

    //判断该月第一天是周几
    public static int getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //判断当前月最后一天周几
    public static int getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

}
