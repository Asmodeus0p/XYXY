package com.jiyun.asmodeus.xyxy.model.biz;



import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by vicoltree on 17/11/23.
 */

public interface IWorkCommentPost {

    @FormUrlEncoded
    @POST("/v1/m/homewok/comments/save")
    Observable<BasicCommentSuccessModel> postWorkComment(@Field("userId") Integer userId,
                                                         @Field("content") String content,
                                                         @Field("refId") int refId);

}
