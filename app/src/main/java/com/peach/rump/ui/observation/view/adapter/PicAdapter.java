package com.peach.rump.ui.observation.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.peach.rump.R;
import com.peach.rump.bean.Pic;
import com.peach.rump.comm.url.AppDebug;
import com.peach.rump.comm.url.HttpConstant;
import com.peachrump.comm.imagePager.BigImagePagerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class PicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Pic> dataList;

    public PicAdapter(List<Pic> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Pic pic = dataList.get(position);
        if (!TextUtils.isEmpty(pic.getUrl())) {
            if (AppDebug.DEBUG) {
                Glide.with(viewHolder.img_pic.getContext()).load(HttpConstant.DEFAULT_PIC).into(viewHolder.img_pic);
            } else {
                Glide.with(viewHolder.img_pic.getContext()).load(pic.getUrl()).into(viewHolder.img_pic);

            }
        }
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> picList = new ArrayList<String>();
                for (int i = 0; i < dataList.size(); i++) {
                    picList.add(dataList.get(i).getUrl());
                }
                if (!AppDebug.DEBUG) {
                    BigImagePagerActivity.startImagePagerActivity((Activity) v.getContext(), picList, position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_pic;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_pic = (ImageView) rootView.findViewById(R.id.img_pic);
        }

    }
}
