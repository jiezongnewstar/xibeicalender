package com.xibei.xibeicalender;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;

public class XiBeiCalenderHolder extends RecyclerView.ViewHolder {

    TextView tv_calender_item;

    public XiBeiCalenderHolder(@NonNull View itemView) {
        super(itemView);
        tv_calender_item = itemView.findViewById(R.id.tv_calender_item);
    }

    public void render(ItemBean item) {
            if (!item.isTitle) {
                if (item.calendar != null) {
                    tv_calender_item.setText(item.calendar.get(Calendar.DAY_OF_MONTH) + "");
                } else {
                    tv_calender_item.setText("");
                }
        }else {
                tv_calender_item.setText("");
            }
    }
}