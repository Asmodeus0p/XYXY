package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.PublishWokDetailBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IPublishWokDetail {


    @FormUrlEncoded
    @POST("/v1/m/homewok/exercises/detail")
    Observable<PublishWokDetailBean> getWokDetail(@Field("id") String id);
}
