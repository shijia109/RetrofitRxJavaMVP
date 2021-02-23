package com.sj.mvprxjavaretrofit.Presenter;

import com.sj.mvprxjavaretrofit.INetCallBack;
import com.sj.mvprxjavaretrofit.Model.ISearchModel;
import com.sj.mvprxjavaretrofit.Model.SearchNetModel;
import com.sj.mvprxjavaretrofit.View.ResultEntity;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by heiyan on 2017/9/12.
 */

public class SearchPresenter extends ISearchPresenter {
    private ISearchModel searchModel;
    private Observer<ResultEntity> observer;
    private Observer<String> observerStr;
    private INetCallBack<ResultEntity> callBack;
    private INetCallBack<String> callBackStr;

    public SearchPresenter() {
        this.searchModel = new SearchNetModel();
        this.callBack = new INetCallBack<ResultEntity>() {
            @Override
            public void onSuccess(ResultEntity data) {
                if (view != null) {
                    view.onSuccess(data.getData());
                    view.hideProgress();
                }
            }

            @Override
            public void onFail(Throwable e) {
                if (view != null) {
                    view.onError("请求失败！" + e.getLocalizedMessage());
                    view.hideProgress();
                }
            }
        };

        observer = new Observer<ResultEntity>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull ResultEntity resultEntity) {
                if (view != null) {
                    view.onSuccess(resultEntity.getData());
                    view.hideProgress();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (view != null) {
                    view.onError("请求失败！" + e.getLocalizedMessage());
                    view.hideProgress();
                }
            }

            @Override
            public void onComplete() {
            }
        };

        observerStr = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(@NonNull String s) {
                if (view != null) {
                    view.onSuccess(convertBean(s, ResultEntity.class).getData());
                    view.hideProgress();
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (view != null) {
                    view.onError("请求失败！" + e.getLocalizedMessage());
                    view.hideProgress();
                }
            }

            @Override
            public void onComplete() {

            }
        };

        callBackStr = new INetCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                if (view != null) {
                    view.onSuccess(convertBean(result, ResultEntity.class).getData());
                    view.hideProgress();
                }
            }

            @Override
            public void onFail(Throwable e) {
                if (view != null) {
                    view.onError("请求失败！" + e.getLocalizedMessage());
                    view.hideProgress();
                }
            }
        };
    }

    @Override
    public void detachView() {
        super.detachView();
        searchModel.destroy();
    }

    @Override
    public void searchBook(String name, String tag, int start, int count) {
        if (view != null)
            view.showProgress();
        searchModel.searchBook1(name, tag, start, count, callBack);
    }

    @Override
    public void searchBook2(String name, String tag, int start, int count) {
        if (view != null)
            view.showProgress();
        searchModel.searchBook2(name, tag, start, count, observer);
    }

    @Override
    public void searchBook3(String name, String tag, int start, int count) {
        if (view != null)
            view.showProgress();
        searchModel.searchBook3(name, tag, start, count, callBack);
    }

    @Override
    public void searchBook4(String name, String tag, int start, int count) {
        if (view != null)
            view.showProgress();
        searchModel.searchBook4(name, tag, start, count, observerStr);
    }

    @Override
    public void searchBook5(String name, String tag, int start, int count) {
        if (view != null)
            view.showProgress();
        searchModel.searchBook5(name, tag, start, count, callBackStr);
    }


}
