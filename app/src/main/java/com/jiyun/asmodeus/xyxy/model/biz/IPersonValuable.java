package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPersonValuable {

    @FormUrlEncoded
    @POST("/v1/m/user/personal/homepage/artcircle")
    Observable<HomeValuanleBean> getValualbeList(@Field("loginUserId") Integer loginUserId,
                                                 @Field("studentId") int studentId,
                                                 @Field("page") int page);
}
