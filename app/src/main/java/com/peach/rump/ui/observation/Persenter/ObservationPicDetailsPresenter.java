package com.peach.rump.ui.observation.Persenter;

import com.peach.rump.bean.ObservationData;
import com.peach.rump.bean.ObservationPic;
import com.peach.rump.bean.Pic;
import com.peach.rump.comm.htmlparser.HtmlParser;
import com.peach.rump.ui.observation.contract.ObservationPicContract;
import com.peach.rump.ui.observation.contract.ObservationPicDetailContract;

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

public class ObservationPicDetailsPresenter extends ObservationPicDetailContract.Presenter {


    public void getBaseObservation(final String url) {

        mView.showLoading(null);
        Flowable.create(new FlowableOnSubscribe<List<Pic>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<List<Pic>> e) throws Exception {



                                    List<Pic> parserpic = HtmlParser.parserpic(url);
                e.onNext(parserpic);
                List<ObservationData> observationDatas = HtmlParser.parserParentpic(url);

//                for (int i = 0; i < observationDatas.size(); i++) {
//                    ObservationPic observationPic=new ObservationPic();
//                    ObservationData observationData = observationDatas.get(i);
//                    observationPic.setTitle(observationData.getTitle());
////                    List<Pic> parserpic = HtmlParser.parserpic(observationData.getUrl());
////                    observationPic.setPicList(parserpic);
//                    e.onNext(observationPic);
//                }
                System.out.println("====="+observationDatas);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Pic>>() {
                    @Override
                    public void accept(@NonNull List<Pic> observationDatas) throws Exception {
                        mView.returnObservationPic(observationDatas);
                        mView.stopLoading();
                    }
                });
    }
}
