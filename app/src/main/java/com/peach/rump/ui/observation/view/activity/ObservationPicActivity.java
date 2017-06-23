package com.peach.rump.ui.observation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;

import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;
import com.peach.rump.ui.observation.Persenter.ObservationPicPresenter;
import com.peach.rump.ui.observation.contract.ObservationPicContract;
import com.peach.rump.ui.observation.model.ObservationPicModel;
import com.peach.rump.ui.observation.view.adapter.ObservationPicAdapter;
import com.peachrump.comm.base.BaseActivity;
import com.peachrump.comm.commonutils.LoadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPicActivity extends BaseActivity<ObservationPicPresenter, ObservationPicModel> implements ObservationPicContract.View {


    private SuperRecyclerView recy_observation;
    private List<ObservationData> dataList;
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
        recy_observation = (SuperRecyclerView) findViewById(R.id.recy_observation);
        dataList = new ArrayList<>();
        adapter = new ObservationPicAdapter(dataList);
        recy_observation.setLayoutManager(new LinearLayoutManager(this));
        recy_observation.setAdapter(adapter);
        Intent intent = getIntent();
        data = (ObservationData) intent.getSerializableExtra("data");
        System.out.println(data.toString());
        mPresenter.getBaseObservation(data.getUrl(), LoadMode.NOMAL);
        recy_observation.setLoadingMore(true);
        recy_observation.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getBaseObservation(data.getUrl(), LoadMode.PULL_REFRSH);

            }
        });
        recy_observation.setupMoreListener(new OnMoreListener() {
            @Override
            public void onMoreAsked(int numberOfItems, int numberBeforeMore, int currentItemPos) {

                mPresenter.getBaseObservation(data.getUrl(), LoadMode.PULL_REFRSH);

            }
        }, 10);
    }


    @Override
    public void returnObservationDatas(List<ObservationData> observationDatas, LoadMode mode) {
        recy_observation.computeScroll();
        if (mode!=LoadMode.UP_REFRESH){
            dataList.clear();
        }
        dataList.addAll(observationDatas);
        adapter.notifyDataSetChanged();
    }

    public static void startAction(Context context, ObservationData observationData) {
        Intent intent = new Intent(context, ObservationPicActivity.class);
        intent.putExtra("data", observationData);

        context.startActivity(intent);
    }
}
