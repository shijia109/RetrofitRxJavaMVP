package com.sj.mvprxjavaretrofit.Model;

import com.sj.mvprxjavaretrofit.INetCallBack;
import com.sj.mvprxjavaretrofit.View.ResultEntity;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by heiyan on 2017/9/12.
 */

public class SearchNetModel extends ISearchModel{

    @Override
    public void searchBook1(String name, String tag, int start, int count, final INetCallBack<ResultEntity> callBack) {
        getService().getSearchBook1(name, tag, start, count).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<ResultEntity>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull ResultEntity resultEntity) {
                        if (callBack != null)
                            callBack.onSuccess(resultEntity);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (callBack != null)
                            callBack.onFail(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void searchBook2(String name, String tag, int start, int count, Observer<ResultEntity> observer) {
        getService().getSearchBook1(name, tag, start, count).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    @Override
    public void searchBook3(String name, String tag, int start, int count, final INetCallBack<ResultEntity> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("queryString", name);
        doPost("http://apk.ruochu.com/2/search", map, new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                if (callBack != null)
                    callBack.onSuccess(convertBean(s, ResultEntity.class));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (callBack != null)
                    callBack.onFail(e);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void searchBook4(String name, String tag, int start, int count, Observer<String> observer) {
        Map<String, String> map = new HashMap<>();
        map.put("queryString", name);
        doPost("http://apk.ruochu.com/2/search", map, observer);
    }

    @Override
    public void searchBook5(String name, String tag, int start, int count, INetCallBack<String> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("queryString", name);
        doPost("http://apk.ruochu.com/2/search", map, callBack);
    }
}
