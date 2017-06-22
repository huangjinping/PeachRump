package com.peach.rump.ui.observation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;
import com.peach.rump.bean.ObservationPic;
import com.peach.rump.bean.Pic;
import com.peach.rump.ui.observation.Persenter.ObservationPicDetailsPresenter;
import com.peach.rump.ui.observation.contract.ObservationPicDetailContract;
import com.peach.rump.ui.observation.model.ObservationDetailsModel;
import com.peach.rump.ui.observation.view.adapter.PicAdapter;
import com.peachrump.comm.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPicDetailsActivity extends BaseActivity<ObservationPicDetailsPresenter, ObservationDetailsModel> implements ObservationPicDetailContract.View {


    private RecyclerView recy_observation;
    private List<Pic> dataList;
    private PicAdapter adapter;
    private ObservationData data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_observationpic;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        recy_observation = (RecyclerView) findViewById(R.id.recy_observation);
        recy_observation.setLayoutManager(new GridLayoutManager(this, 3));
        Intent intent = getIntent();
        data = (ObservationData) intent.getSerializableExtra("data");
        dataList=new ArrayList<>();
         adapter = new PicAdapter(dataList);
        recy_observation.setLayoutManager(new LinearLayoutManager(this));
        recy_observation.setAdapter(adapter);
        mPresenter.getBaseObservation(data.getUrl());

    }



    public static void startAction(Context context, ObservationData observationData) {
        Intent intent = new Intent(context, ObservationPicDetailsActivity.class);
        intent.putExtra("data", observationData);

        context.startActivity(intent);
    }

    @Override
    public void returnObservationPic(List<Pic> observationDatas) {
        dataList.addAll(observationDatas);
        adapter.notifyDataSetChanged();
    }
}
