package com.sj.mvprxjavaretrofit;

import com.sj.mvprxjavaretrofit.View.ResultEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by heiyan on 2017/9/8.
 */

public interface RetrofitService{
    @GET("book/search")
    Observable<ResultEntity> getSearchBook(@Query("q") String name,
                                           @Query("tag") String tag,
                                           @Query("start") int start,
                                           @Query("count") int count);

    @GET("http://apk.ruoxia.com/2/search")
    Observable<ResultEntity> getSearchBook1(@Query("queryString") String name,
                                   @Query("tag") String tag,
                                   @Query("start") int start,
                                   @Query("count") int count);

    @GET()
    Observable<String> get(@Url String url, @QueryMap Map<String, String> params);

    @POST
    @FormUrlEncoded
    Observable<String> post(@Url String url, @FieldMap Map<String, String> params);
}
