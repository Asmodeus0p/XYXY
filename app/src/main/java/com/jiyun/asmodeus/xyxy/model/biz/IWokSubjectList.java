package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.WokSubjectListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IWokSubjectList {

    @FormUrlEncoded
    @POST("/v1/m/homewok/exercises")
    Observable<WokSubjectListBean> getWokSubjectList(@Field("loginUserId") Integer loginUserId,
                                                     @Field("page") int page);
}
