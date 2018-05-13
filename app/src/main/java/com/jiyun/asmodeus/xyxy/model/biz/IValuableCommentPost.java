package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IValuableCommentPost {

    @FormUrlEncoded
    @POST("/v1/m/artcircle/comments/save")
    Observable<BasicCommentSuccessModel> postValuableComment(@Field("userId") Integer userId,
                                                             @Field("content") String content,
                                                             @Field("refId") int refId);
}
