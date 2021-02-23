package com.sj.mvprxjavaretrofit.Model;

import android.support.annotation.CallSuper;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.sj.mvprxjavaretrofit.INetCallBack;
import com.sj.mvprxjavaretrofit.RetrofitHelper;
import com.sj.mvprxjavaretrofit.RetrofitService;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by heiyan on 2018/4/28.
 */

public abstract class BaseNetModel {
//    private RetrofitHelper retrofitHelper;
    private CompositeDisposable disposables;
    private RetrofitService service;
    private Gson gson = new Gson();

    BaseNetModel() {
//        retrofitHelper = RetrofitHelper.getInstance();
        disposables = new CompositeDisposable();
        service = RetrofitHelper.getInstance().createService();
    }

    public RetrofitService getService() {
        return service;
    }

    public void addDisposable(Disposable d) {
        disposables.add(d);
    }

    public <T> T convertBean(String str, Class<T> cls){
        return gson.fromJson(str, cls);
    }

    public void doGet(String url, Map<String, String> map, Observer<String> observer) {
        if (TextUtils.isEmpty(url))
            return;
        if (!url.startsWith("http")) {
            url = "http://apk.ruochu.com/2/" + url;
        }
        service.get(url, map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    public void doPost(String url, Map<String, String> map, Observer<String> observer) {
        if (TextUtils.isEmpty(url))
            return;
        if (!url.startsWith("http")) {
            url = "http://apk.ruochu.com/2/" + url;
        }
        service.post(url, map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(observer);
    }

    public void doPost(String url, Map<String, String> map, final INetCallBack<String> callBack) {
        if (TextUtils.isEmpty(url))
            return;
        if (!url.startsWith("http")) {
            url = "http://apk.ruochu.com/2/" + url;
        }
        service.post(url, map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if (callBack != null)
                            callBack.onSuccess(s);
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

    public void doGet(String url, Map<String, String> map, final INetCallBack<String> callBack) {
        if (TextUtils.isEmpty(url))
            return;
        if (!url.startsWith("http")) {
            url = "http://apk.ruochu.com/2/" + url;
        }
        service.get(url, map).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull String s) {
                        if (callBack != null)
                            callBack.onSuccess(s);
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

    @CallSuper
    public void destroy() {
        disposables.clear();
        disposables = null;
        gson = null;
        service = null;
    }
}
