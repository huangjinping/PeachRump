package com.peach.rump.ui.direct.view.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.peach.rump.R;
import com.peach.rump.bean.ObservationData;
import com.peach.rump.ui.direct.Presenter.DirectPresenter;
import com.peach.rump.ui.direct.contract.DirectContract;
import com.peach.rump.ui.direct.model.DirectModel;
import com.peach.rump.ui.observation.view.adapter.ObservationAdapter;
import com.peachrump.comm.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/6/20.
 * email : huangjinping@hdfex.com
 */

public class DirectParentFragment extends BaseFragment<DirectPresenter, DirectModel> implements DirectContract.View {



    private RecyclerView recy_observation;
    private ObservationAdapter adapter;
    private List<ObservationData> dataList;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_directparent;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
        mPresenter.getBaseObservation();
    }

    @Override
    protected void initView() {
        recy_observation = (RecyclerView) rootView.findViewById(R.id.recy_observation);
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new ObservationData());
        }
        adapter = new ObservationAdapter(dataList);
        recy_observation.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        recy_observation.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recy_observation.setAdapter(adapter);

    }


    @Override
    public void returnObservationDatas(List<ObservationData> observationData) {
        dataList.clear();
        dataList.addAll(observationData);
        adapter.notifyDataSetChanged();
    }
}
