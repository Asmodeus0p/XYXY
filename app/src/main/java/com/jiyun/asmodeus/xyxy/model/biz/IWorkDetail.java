package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface IWorkDetail {

    @FormUrlEncoded
    @POST("/v1/m/homewok/detail")
    Observable<WokDetailBean> loadWorkDetail(@Field("homewokId") int homewokId,
                                             @Field("loginUserId") Integer loginUserId);

}
