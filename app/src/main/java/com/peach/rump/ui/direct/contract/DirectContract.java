package com.peach.rump.ui.direct.contract;

import com.peach.rump.bean.ObservationData;
import com.peachrump.comm.base.BaseModel;
import com.peachrump.comm.base.BasePresenter;
import com.peachrump.comm.base.BaseView;

import java.util.List;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public interface DirectContract {

    interface Model extends BaseModel {

    }

    interface View extends BaseView {

        void returnObservationDatas(List<ObservationData> observationData);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {


    }
}
