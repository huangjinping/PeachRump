package com.peach.rump.ui.observation.Persenter;

import com.peach.rump.bean.ObservationData;
import com.peach.rump.bean.ObservationPic;
import com.peach.rump.bean.Pic;
import com.peach.rump.comm.htmlparser.HtmlParser;
import com.peach.rump.ui.observation.contract.ObservationPicContract;
import com.peachrump.comm.commonutils.LoadMode;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static org.jsoup.nodes.Entities.EscapeMode.base;

/**
 * author Created by harrishuang on 2017/6/21.
 * email : huangjinping@hdfex.com
 */

public class ObservationPicPresenter extends ObservationPicContract.Presenter {


    public void getBaseObservation(final String url,final LoadMode mode) {

        mView.showLoading(null);
        Flowable.create(new FlowableOnSubscribe<List<ObservationData>>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<List<ObservationData>> e) throws Exception {

                List<ObservationData> observationDatas = HtmlParser.parserParentpic(url);

                for (int i = 0; i < observationDatas.size(); i++) {
                    ObservationPic observationPic=new ObservationPic();
                    ObservationData observationData = observationDatas.get(i);
                    observationPic.setTitle(observationData.getTitle());
//                    List<Pic> parserpic = HtmlParser.parserpic(observationData.getUrl());
//                    observationPic.setPicList(parserpic);
                    observationPic.setUrl(observationData.getUrl());
                    e.onNext(observationDatas);
                }
                System.out.println("====="+observationDatas);
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<ObservationData>>() {
                    @Override
                    public void accept(@NonNull List<ObservationData> observationDatas) throws Exception {
                        mView.returnObservationDatas(observationDatas,mode);
                        mView.stopLoading();
                    }
                });
    }
}
