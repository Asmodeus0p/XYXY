package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IAttentionCancle {

    @FormUrlEncoded
    @POST("/v1/m/user/attention/cancel")
    Observable<BasicSuccessBean> postAttention(@Field("attentionId") int attentionId,
                                               @Field("loginUserId") Integer loginUserId);
}
