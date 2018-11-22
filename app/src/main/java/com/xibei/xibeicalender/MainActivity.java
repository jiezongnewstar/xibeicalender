package com.xibei.xibeicalender;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_calender;
    XIbeiCalenderAdapter xIbeiCalenderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView(){
        ((TextView) findViewById(R.id.ic_mon)).setText("一");
        ((TextView) findViewById(R.id.ic_thu)).setText("二");
        ((TextView) findViewById(R.id.ic_wen)).setText("三");
        ((TextView) findViewById(R.id.ic_thu)).setText("四");
        ((TextView) findViewById(R.id.ic_fri)).setText("五");
        ((TextView) findViewById(R.id.ic_sat)).setText("六");
        ((TextView) findViewById(R.id.ic_sun)).setText("日");
        rv_calender = findViewById(R.id.rv_calender);

        initData();
    }

    private void initData() {

        xIbeiCalenderAdapter = new XIbeiCalenderAdapter(Calendar.getInstance());
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
}
