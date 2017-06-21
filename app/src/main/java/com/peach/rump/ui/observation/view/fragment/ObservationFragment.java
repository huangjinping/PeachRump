package com.peach.rump.ui.observation.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;
import com.peach.rump.ui.observation.view.adapter.ObservationAdapter;
import com.peachrump.comm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationFragment extends BaseFragment {

    private RecyclerView recy_observation;
    private ObservationAdapter adapter;
    private List<ObservationData> dataList;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_observation;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        recy_observation=(RecyclerView)rootView.findViewById(R.id.recy_observation);
        dataList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            dataList.add(new ObservationData());
        }
        adapter=new ObservationAdapter(dataList);
        recy_observation.setLayoutManager(new LinearLayoutManager(getContext()));
        recy_observation.setAdapter(adapter);

    }


}
