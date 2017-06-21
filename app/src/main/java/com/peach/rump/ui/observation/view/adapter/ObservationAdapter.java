package com.peach.rump.ui.observation.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;

import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ObservationData> dataList;

    public ObservationAdapter(List<ObservationData> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_obervation, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        ObservationData observationData = dataList.get(position);
        if (!TextUtils.isEmpty(observationData.getTitle())){
            viewHolder.txt_title.setText(observationData.getTitle());
        }
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_title;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_title = (TextView) rootView.findViewById(R.id.txt_title);
        }

    }
}
