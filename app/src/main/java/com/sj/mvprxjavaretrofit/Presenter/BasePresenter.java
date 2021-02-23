package com.sj.mvprxjavaretrofit.Presenter;

import android.support.annotation.CallSuper;

import com.google.gson.Gson;
import com.sj.mvprxjavaretrofit.View.IBaseView;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by heiyan on 2018/4/28.
 */

public abstract class BasePresenter<T extends IBaseView> {
    protected T view;
    private CompositeDisposable disposables;
    private WeakReference<T> weakReference;
    private Gson gson;

    BasePresenter() {
        disposables = new CompositeDisposable();
        gson =  new Gson();
    }

    protected void addDisposable(Disposable d) {
        disposables.add(d);
    }

    public <T> T convertBean(String str, Class<T> cls){
        return gson.fromJson(str, cls);
    }

    @CallSuper
    public void attachView(T baseIView) {
        weakReference = new WeakReference<>(baseIView);
        view = weakReference.get();
    }

    @CallSuper
    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        view = null;
        disposables.clear();
    }
}
