package com.peach.rump.ui.observation.view.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationPic;

import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ObservationPic> dataList;

    public ObservationPicAdapter(List<ObservationPic> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_obervationpic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        ObservationPic observationPic = dataList.get(position);
        if (!TextUtils.isEmpty(observationPic.getTitle())) {
            viewHolder.txt_title.setText(observationPic.getTitle());
        }
        viewHolder.recy_pic.setLayoutManager(new GridLayoutManager(viewHolder.txt_title.getContext(), 3));
        PicAdapter adapter = new PicAdapter(observationPic.getPicList());
        viewHolder.recy_pic.setAdapter(adapter);


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_title;
        public RecyclerView recy_pic;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_title = (TextView) rootView.findViewById(R.id.txt_title);
            this.recy_pic = (RecyclerView) rootView.findViewById(R.id.recy_pic);
        }

    }
}
