package com.xibei.xibeicalender;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import static com.xibei.xibeicalender.R.drawable.item_bg;

public class XiBeiCalenderHolder extends RecyclerView.ViewHolder {

    TextView tv_calender_item;

    public XiBeiCalenderHolder(@NonNull View itemView) {
        super(itemView);
        tv_calender_item = itemView.findViewById(R.id.tv_calender_item);
    }

    public void render(final ItemBean item) {
            if (!item.isTitle) {
                if (item.calendar != null) {
                    tv_calender_item.setText(item.calendar.get(Calendar.DAY_OF_MONTH) + "");
                    if (item.isToday){
                        tv_calender_item.setTextColor(itemView.getContext().getResources().getColor(R.color.bg_yellow));
                    }
                    if (item.isSelected){
                        tv_calender_item.setTextColor(Color.WHITE);
                        tv_calender_item.setBackgroundResource(R.drawable.item_bg);
                    }

                    if (item.canSelect){
                        itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(itemView.getContext(),
                                        item.calendar.get(Calendar.YEAR)+"-"
                                                +(item.calendar.get(Calendar.MONTH)+1)+"-"
                                                +item.calendar.get(Calendar.DAY_OF_MONTH),
                                        Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                } else {
                    tv_calender_item.setText("");
                }
        }else {
                tv_calender_item.setText("");
            }
    }
}
