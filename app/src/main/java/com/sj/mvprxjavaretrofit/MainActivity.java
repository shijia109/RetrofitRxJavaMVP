package com.sj.mvprxjavaretrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sj.mvprxjavaretrofit.Presenter.ISearchPresenter;
import com.sj.mvprxjavaretrofit.Presenter.SearchPresenter;
import com.sj.mvprxjavaretrofit.View.SearchView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.reactivestreams.Subscriber;

import java.util.List;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;


public class MainActivity extends RxAppCompatActivity implements SearchView{
    private static  final String TAG = "MainActivity--->";
    private TextView textView;
    private Button button;
    private Subscriber<Book> subscriber;
    private ISearchPresenter presenter;
    private ProgressDialog progressDialog;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("--->onCreate");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.d(TAG, "throw test");
            }
        });
        handler = new Handler(getMainLooper());

        presenter = new SearchPresenter();
        presenter.attachView(this);

        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.searchBook("鬼", null, 0, 1);
            }
        });
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.searchBook2("的", null, 0, 1);
                    }
                }, 0);

            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.searchBook3("你", null, 0, 1);
                    }
                }, 0);

            }
        });

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.searchBook4("命", null, 0, 1);
                    }
                }, 0);

            }
        });

        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.searchBook5("事", null, 0, 1);
                    }
                }, 0);

            }
        });

/*        Observable.interval(1, TimeUnit.SECONDS)
                .doOnDispose(new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.i(TAG, "Unsubscribing subscription from onCreate()");
                    }
                })
                .compose(this.<Long>bindUntilEvent(ActivityEvent.STOP))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long num) throws Exception {
                        Log.i(TAG, "Started in onCreate(), running until in onDestroy(): " + num);
                    }
                });*/

/*        SearchModel searchModel = new SearchModelImp();
        searchModel.searchBook1("水浒传", null, 0, 1).
                delay(5, TimeUnit.SECONDS).
                compose(this.<Book>bindUntilEvent(ActivityEvent.DESTROY)).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Book>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Book book) {
                        mBook = book;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
//                        searchView.onError("请求失败！");
//                        searchView.hideProgress();
                    }

                    @Override
                    public void onComplete() {
                        if (mBook != null){
//                            searchView.onSuccess(mBook);
                            System.out.println("--->book="+mBook);
                        }
//                        searchView.hideProgress();
                    }
                });*/


        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();


        SearchRetrofitService service = retrofit.create(SearchRetrofitService.class);*/


        /*Call<Book> call = service.getSearchBook("金瓶梅", null, 0, 1);
        call.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                textView.setText(response.body() + "");
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });*/

/*        subscriber = new Subscriber<Book>() {
            @Override
            public void onCompleted() {
                System.out.println("--->onCompleted "+Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("--->onError "+Thread.currentThread().getName());
            }

            @Override
            public void onNext(Book book) {
                System.out.println("--->onNext "+Thread.currentThread().getName());
                mBook = book;
                textView.setText(mBook.toString());
            }
        };

        Observable<Book> observable =  service.getSearchBook("金瓶梅", null, 0, 1);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);*/





    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("--->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("--->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("--->onDestroy");
        presenter.detachView();
    }


    @Override
    public void onSuccess(List<Book> bookList) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < bookList.size(); i++) {
            builder.append(bookList.get(i).getName()).append("\n");
        }
        textView.setText(builder.toString());
    }

    @Override
    public void onError(String result) {
        textView.setText(result);
    }

    @Override
    public void showProgress() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("数据请求中，请稍后...");
        }
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }
}
