package com.sj.mvprxjavaretrofit;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.sunshine.retrofit.converter.StringConverterFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heiyan on 2017/9/12.
 */

public class RetrofitHelper {
    private static final String DEFAULT_BASE_URL = "http://apk.heiyan.com/2";
    private static final int READ_TIME_OUT = 15;//读超时
    private static final int CONN_TIME_OUT = 15;//链接超时
    private static final int WRITE_TIME_OUT = 15;//写超时
    private GsonConverterFactory gsonConverterFactory;
    private RxJava2CallAdapterFactory rxJava2CallAdapterFactory;
    private StringConverterFactory stringConverterFactory;
    private Map<String, Retrofit> retrofitMap;
//    private Map<Retrofit, RetrofitService> serviceMap;
    private OkHttpClient okHttpClient;
    private RetrofitService service;

    public static RetrofitHelper getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder{
        public static  final RetrofitHelper instance = new RetrofitHelper();
    }

    private RetrofitHelper() {
        init();
    }

    private void init() {
        getOkHttpClient();
        gsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().create());
        rxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create();
        stringConverterFactory = StringConverterFactory.create();
//        serviceMap = new HashMap<>();
        retrofitMap = new HashMap<>();
        retrofitMap.put(DEFAULT_BASE_URL, getRetrofitNewInstance(DEFAULT_BASE_URL));
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null){
            okHttpClient = getOkHttpClientNewInstance();
        }
        return okHttpClient;
    }

    private OkHttpClient getOkHttpClientNewInstance() {
        OkHttpClient.Builder builder = new OkHttpClient
                .Builder()
                .connectTimeout(CONN_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }

    private Retrofit getRetrofitNewInstance(String baseUrl, OkHttpClient okHttpClient) {
        if (!baseUrl.endsWith("/"))
            baseUrl = baseUrl + "/";

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(stringConverterFactory)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .build();
    }

    public Retrofit getRetrofit() {
        return getRetrofit(DEFAULT_BASE_URL);
    }


    public Retrofit getRetrofit(String baseUrl) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            synchronized (this) {
                retrofit = getRetrofitNewInstance(baseUrl);
                retrofitMap.put(baseUrl, retrofit);
                System.out.println("--->没有找到 " + baseUrl + " 对应retrofit，新建并缓存");
            }
        } else {
            System.out.println("--->找到 " + baseUrl + " 对应retrofit，直接返回");
        }
        return retrofit;
    }

    private Retrofit getRetrofitNewInstance(String baseUrl) {
        return getRetrofitNewInstance(baseUrl, getOkHttpClient());
    }

    public RetrofitService createService(Retrofit retrofit) {
        if (retrofit == null)
            retrofit = getRetrofit();
        if (service == null) {
            synchronized (this) {
                if (service == null) {
                    service = retrofit.create(RetrofitService.class);
                }
            }
        }
        return service;
    }

    public RetrofitService createService() {
       return createService(getRetrofit());
    }
}
