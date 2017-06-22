package com.peach.rump.ui.observation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;
import com.peach.rump.bean.ObservationPic;
import com.peach.rump.ui.observation.Persenter.ObservationPicPresenter;
import com.peach.rump.ui.observation.contract.ObservationPicContract;
import com.peach.rump.ui.observation.model.ObservationPicModel;
import com.peach.rump.ui.observation.view.adapter.ObservationPicAdapter;
import com.peachrump.comm.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPicActivity extends BaseActivity<ObservationPicPresenter, ObservationPicModel> implements ObservationPicContract.View {


    private RecyclerView recy_observation;
    private List<ObservationPic> dataList;
    private ObservationPicAdapter adapter;
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
        dataList = new ArrayList<>();
        adapter = new ObservationPicAdapter(dataList);
        recy_observation.setLayoutManager(new LinearLayoutManager(this));
        recy_observation.setAdapter(adapter);
        Intent intent = getIntent();
        data = (ObservationData) intent.getSerializableExtra("data");
        System.out.println(data.toString());
        mPresenter.getBaseObservation(data.getUrl());
    }


    @Override
    public void returnObservationDatas(ObservationPic observationData) {
        dataList.add(observationData);
        adapter.notifyDataSetChanged();
    }

    public static void startAction(Context context, ObservationData observationData) {
        Intent intent = new Intent(context, ObservationPicActivity.class);
        intent.putExtra("data", observationData);

        context.startActivity(intent);
    }
}
