package com.xibei.xibeicalender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.xibei.xibeicalender.Util.getMonthFirstDay;
import static com.xibei.xibeicalender.Util.getMonthLastDay;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_calender;
    XIbeiCalenderAdapter xIbeiCalenderAdapter;
    private List<ItemBean> dataList = new ArrayList<>();
    private int year, month, day;
    private Calendar nowCalender = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView(){
        ((TextView) findViewById(R.id.ic_mon)).setText("一");
        ((TextView) findViewById(R.id.ic_tue)).setText("二");
        ((TextView) findViewById(R.id.ic_wen)).setText("三");
        ((TextView) findViewById(R.id.ic_thu)).setText("四");
        ((TextView) findViewById(R.id.ic_fri)).setText("五");
        ((TextView) findViewById(R.id.ic_sat)).setText("六");
        ((TextView) findViewById(R.id.ic_sun)).setText("日");
        rv_calender = findViewById(R.id.rv_calender);

        initListener();
        initData(0);
        initData(2);
        initData(3);
    }

    //初始化数据
    private void initData(int afterMonth) {

        //获取当前年月日
        year = nowCalender.get(Calendar.YEAR);
        month = nowCalender.get(Calendar.MONTH);
        day = nowCalender.get(Calendar.DAY_OF_MONTH);
        Log.e("日历", "当前年月日" + year + "-" + month + "-" + day);

        if (afterMonth == 0) {
            initLiocalMonth(afterMonth);
        } else {
            initNextMonth(afterMonth);
        }

    }



    private void initListener() {

        xIbeiCalenderAdapter = new XIbeiCalenderAdapter(Calendar.getInstance(),dataList);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this,7);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                int type = xIbeiCalenderAdapter.getItemViewType(i);
                if (type == 1) {
                    return gridLayoutManager.getSpanCount();
                } else {
                    return 1;
                }
            }
        });

        rv_calender.setLayoutManager(gridLayoutManager);
        rv_calender.setAdapter(xIbeiCalenderAdapter);
    }




    //添加当月
    private void initLiocalMonth(int afterMonth) {

        //添加当月标题
        dataList.add(new ItemBean(nowCalender, true, false, false));

        //判断第一天是周几 添加月首站位
        if (getMonthFirstDay(afterMonth) != 7) {
            for (int i = 0; i < getMonthFirstDay(afterMonth); i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        }

        //添加具体日期
        int maxDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        for (int i = 0; i <maxDay ; i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.DAY_OF_MONTH, i + 1);

            //是否是今天
            if (day == calendar.get(Calendar.DAY_OF_MONTH)) {
                dataList.add(new ItemBean(calendar, false, true, true));
            } else {
                dataList.add(new ItemBean(calendar, false, false, false));
            }
        }

        //添加月尾站位
        if (getMonthLastDay(afterMonth) != 7) {
            for (int i = 0; i < 6 - getMonthLastDay(afterMonth); i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        }
    }


    //添加次月
    private void initNextMonth(int afterMonth) {
        Calendar nextCalender = Calendar.getInstance();
        nextCalender.set(Calendar.MONTH, month + afterMonth);
        nextCalender.set(Calendar.DAY_OF_MONTH, 0);

        Log.e("日历", "添加的第" + afterMonth + "对应的时间"
                + nextCalender.get(Calendar.YEAR) + "-" +
                +nextCalender.get(Calendar.MONTH) + "-" +
                +nextCalender.get(Calendar.DAY_OF_MONTH));

        int firstDay = getMonthFirstDay(afterMonth-1);
        int lastDay = getMonthLastDay(afterMonth-1);

        Log.e("日历", "第一天" + firstDay + "最后一天" + lastDay);

        //添加当月标题
        dataList.add(new ItemBean(nextCalender, true, false, false));

        //判断第一天是周几 添加月首站位
        if (firstDay != 7) {
            for (int i = 0; i < firstDay; i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }

        }

        //添加具体日期

        int maxDay = nextCalender.getActualMaximum(Calendar.DAY_OF_MONTH);
        Log.e("日历","被添加的最大天数" + +maxDay);
        for (int i = 0; i < maxDay ; i++) {
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.MONTH, month + afterMonth-1);
            calender.set(Calendar.DAY_OF_MONTH, i+1);
            Log.e("日历","被添加的i"+i);
            dataList.add(new ItemBean(calender, false, false, false));
        }

        //添加月尾站位
        if (lastDay != 7) {
            for (int i = 0; i < 6 - lastDay; i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        } else {
            for (int i = 0; i < 6; i++) {
                dataList.add(new ItemBean(null, false, false, false));
            }
        }

    }
}
