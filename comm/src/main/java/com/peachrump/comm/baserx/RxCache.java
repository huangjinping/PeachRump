package com.peachrump.comm.baserx;

import android.content.Context;

import com.peachrump.comm.commonutils.ACache;

import java.io.Serializable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * des:处理服务器数据的缓存
 * Created by xsf
 * on 2016.09.11:01
 */
//################################使用例子#############################
/*
Observable<LoginData> fromNetwork = Api.getDefault()
        .login(phone, password)
        .compose(RxHelper.handleResult());

        RxCache.load(context,cacheKey,1000*60*30,fromNetwork,false)
        .subscribe(new RxSubscribe<LoginData>(context, "登录中...") {
@Override
protected void _onNext(LoginData data) {
        showToast(R.string.login_success);
        //TODO login success
        }

@Override
protected void _onError(String message) {
        showToast(message);
        }
        });
 */


public class RxCache {
    /**
     * @param context
     * @param cacheKey
     * @param expireTime
     * @param fromNetwork
     * @param forceRefresh
     * @param <T>
     * @return
     */
    public static <T> Observable<T> load(final Context context,
                                         final String cacheKey,
                                         final int expireTime,
                                         Observable<T> fromNetwork,
                                         boolean forceRefresh) {
        Observable<T> fromCache = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<T> e) throws Exception {
                //获取缓存
                T cache = (T) ACache.get(context).getAsObject(cacheKey);
                if (cache != null) {
                    e.onNext(cache);
                } else {
                    e.onComplete();
                }
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        /**
         * 这里的fromNetwork 不需要指定Schedule,在handleRequest中已经变换了
         */
        fromNetwork = fromNetwork.map(new Function<T, T>() {
            @Override
            public T apply(@NonNull T result) throws Exception {
                //保存缓存
                ACache.get(context).put(cacheKey, (Serializable) result, expireTime);
                return result;
            }


        });
        //强制刷新则返回接口数据
        if (forceRefresh) {
            return fromNetwork;
        } else {
            //优先返回缓存
            return Observable.concat(fromCache, fromNetwork).cache();

        }
    }
}
