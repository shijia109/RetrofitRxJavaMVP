package com.sj.mvprxjavaretrofit.Presenter;

import android.view.View;

import com.sj.mvprxjavaretrofit.View.SearchView;

/**
 * Created by heiyan on 2017/9/12.
 */

public abstract class ISearchPresenter extends BasePresenter<SearchView>{
    public abstract void searchBook(String name, String tag, int start, int count);
    public abstract void searchBook2(String name, String tag, int start, int count);
    public abstract void searchBook3(String name, String tag, int start, int count);
    public abstract void searchBook4(String name, String tag, int start, int count);
    public abstract void searchBook5(String name, String tag, int start, int count);

}
