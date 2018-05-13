package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IHomeWork {

    @FormUrlEncoded
    @POST("/v1/m/homewok/home")
    Observable<HomeWokListBean> gethomewokData(@Field("loginUserId") Integer loginUserId,
                                               @Field("page") int page,
                                               @Field("sortord") int sortord);
}
