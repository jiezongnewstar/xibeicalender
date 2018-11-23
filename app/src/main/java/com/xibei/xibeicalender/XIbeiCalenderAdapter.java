package com.xibei.xibeicalender;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
/**
 * 日历  recycleView  adapter
 */
public class XIbeiCalenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public int TITLE = 1; //展示月份标题
    public int ITEM = 2;  //展示详细月份

    private List<ItemBean> dataList;

    public XIbeiCalenderAdapter(List<ItemBean> dataList) {
        this.dataList = dataList;
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



}
