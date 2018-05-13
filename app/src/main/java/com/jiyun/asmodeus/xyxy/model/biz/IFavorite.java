package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IFavorite {


    @FormUrlEncoded
    @POST("/v1/m/user/favorite")
    Observable<BasicSuccessBean> favorite(@Field("id") int id,
                                          @Field("loginUserId") Integer loginUserId,
                                          @Field("type") String type);
}
