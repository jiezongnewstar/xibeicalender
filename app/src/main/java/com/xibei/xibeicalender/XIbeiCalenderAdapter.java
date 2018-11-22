package com.xibei.xibeicalender;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.xibei.xibeicalender.Util.getMonthFirstDay;
import static com.xibei.xibeicalender.Util.getMonthLastDay;

/**
 * 日历  recycleView  adapter
 */
public class XIbeiCalenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int TITLE = 1; //展示月份标题
    public int ITEM = 2;  //展示详细月份
    private Calendar inComeCalender = null; //传入参数
    private Calendar nowCalender;
    private List<ItemBean> dataList = new ArrayList<>();

    public XIbeiCalenderAdapter(Calendar inComeCalender) {
        nowCalender = Calendar.getInstance();
        this.inComeCalender = inComeCalender;
        initData();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 1) {
            View title = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calender_title,
                    viewGroup, false);
            return new XIBeiTitleHolder(title);
        } else {
            View item = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_calender,
                    viewGroup, false);
            return new XiBeiCalenderHolder(item);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof XIBeiTitleHolder) {
            ((XIBeiTitleHolder) viewHolder).render(dataList.get(i));
        } else if (viewHolder instanceof XiBeiCalenderHolder) {
            ((XiBeiCalenderHolder) viewHolder).render(dataList.get(i));
        }
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList.get(position).isTitle) {

            return TITLE;
        }
        return ITEM;
    }


    //初始化数据
    private void initData() {

        //获取当前年月日
        int year = nowCalender.get(Calendar.YEAR);
        int month = nowCalender.get(Calendar.MONTH);
        int day = nowCalender.get(Calendar.DAY_OF_MONTH);

        //添加当月标题
        dataList.add(new ItemBean(nowCalender, true, false, false));

        //判断第一天是周几 添加月首站位
        Log.e("日历","第一天周几"+getMonthFirstDay());

        if (getMonthFirstDay() != 7) {
            for (int i = 0; i < getMonthFirstDay(); i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        }

        //添加具体日期
        for (int i = 0; i < nowCalender.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            Calendar firstMonthCalender = Calendar.getInstance();
            firstMonthCalender.set(Calendar.YEAR, year);
            firstMonthCalender.set(Calendar.MONTH, month);
            firstMonthCalender.set(Calendar.DAY_OF_MONTH, i + 1);
            //是否是今天
            if (day == firstMonthCalender.get(Calendar.DAY_OF_MONTH)) {
                dataList.add(new ItemBean(firstMonthCalender, false, true, false));
            } else {
                dataList.add(new ItemBean(firstMonthCalender, false, false, false));
            }
        }


        //添加月尾站位
        Log.e("日历","最后一天周几"+getMonthLastDay());
        if (getMonthLastDay() != 7) {
            for (int i = 0; i < 6 - getMonthLastDay(); i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        }
        Log.e("日历", "数据源"+dataList.size() + "");
    }
}
