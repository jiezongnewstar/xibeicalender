package com.xibei.xibeicalender;

import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class XIBeiTitleHolder extends RecyclerView.ViewHolder {

    TextView tv_calender_title;

    public XIBeiTitleHolder(@NonNull View itemView) {
        super(itemView);
        tv_calender_title = itemView.findViewById(R.id.tv_calender_title);
    }

    public void render(ItemBean title){
        if (title.isTitle){
            tv_calender_title
                    .setText(title.calendar.get(Calendar.YEAR)+"-"
                            +title.calendar.get(Calendar.MONTH)+"-"
                            +title.calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
}
