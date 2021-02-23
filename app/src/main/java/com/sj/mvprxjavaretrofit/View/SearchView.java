package com.sj.mvprxjavaretrofit.View;

import com.sj.mvprxjavaretrofit.Book;

import java.util.List;

/**
 * Created by heiyan on 2017/9/12.
 */

public interface SearchView extends IBaseView {
    void onSuccess(List<Book> bookList);

    void onError(String result);

    void showProgress();

    void hideProgress();
}
