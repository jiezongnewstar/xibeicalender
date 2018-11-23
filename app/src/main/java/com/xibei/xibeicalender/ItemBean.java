package com.xibei.xibeicalender;

import java.util.Calendar;

/**
 * 日历item封装类
 */
public class ItemBean {
    public Calendar calendar;    //具体时间
    public boolean  isTitle;     //是否为标题
    public boolean  isToday;     //是否是今天
    public boolean  isSelected;  //是否是选中状态
    public boolean  canSelect;

    public ItemBean( Calendar calendar, boolean isTitle, boolean isToday, boolean isSelected,boolean canSelect) {
        this.calendar = calendar;
        this.isTitle = isTitle;
        this.isToday = isToday;
        this.isSelected = isSelected;
        this.canSelect = canSelect;
    }
}
