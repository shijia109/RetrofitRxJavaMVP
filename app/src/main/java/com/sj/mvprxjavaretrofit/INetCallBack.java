package com.sj.mvprxjavaretrofit;

/**
 * Created by heiyan on 2018/5/3.
 */

public interface INetCallBack<T> {
    void onSuccess(T result);
    void onFail(Throwable e);
}
