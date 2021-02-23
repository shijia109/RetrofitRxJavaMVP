package com.sj.mvprxjavaretrofit.Model;

import com.sj.mvprxjavaretrofit.Book;
import com.sj.mvprxjavaretrofit.INetCallBack;
import com.sj.mvprxjavaretrofit.RetrofitService;
import com.sj.mvprxjavaretrofit.View.ResultEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;


/**
 * Created by heiyan on 2017/9/12.
 */

public abstract class ISearchModel extends BaseNetModel{
    public abstract void searchBook1(String name, String tag, int start, int count, INetCallBack<ResultEntity> callBack);
    public abstract void searchBook2(String name, String tag, int start, int count, Observer<ResultEntity> observer);
    public abstract void searchBook3(String name, String tag, int start, int count, INetCallBack<ResultEntity> callBack);
    public abstract void searchBook4(String name, String tag, int start, int count, Observer<String> observer);
    public abstract void searchBook5(String name, String tag, int start, int count, INetCallBack<String> callBack);
}
