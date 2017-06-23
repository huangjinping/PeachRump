package com.peach.rump.ui.direct.Presenter;

import com.peach.rump.bean.ObservationData;
import com.peach.rump.comm.htmlparser.HtmlParser;
import com.peach.rump.comm.url.HttpConstant;
import com.peach.rump.ui.direct.contract.DirectContract;
import com.peach.rump.ui.observation.contract.ObservationContract;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class DirectPresenter extends DirectContract.Presenter {


    public void getBaseObservation() {
        System.out.println("==getBaseObservation==" + Thread.currentThread().getName());

        mView.showLoading(null);

        Flowable.create(new FlowableOnSubscribe<List<ObservationData>>() {

            @Override
            public void subscribe(@NonNull FlowableEmitter<List<ObservationData>> e) throws Exception {
                List<ObservationData> observationDatas = HtmlParser.parserYinqi(HttpConstant.YINQI_91);
                e.onNext(observationDatas);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ObservationData>>() {
                    @Override
                    public void accept(@NonNull List<ObservationData> observationDatas) throws Exception {
                        mView.returnObservationDatas(observationDatas);
                        mView.stopLoading();
                    }
                });
    }
}
