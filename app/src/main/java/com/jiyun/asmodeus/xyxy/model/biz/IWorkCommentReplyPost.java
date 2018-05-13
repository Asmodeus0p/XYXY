package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.BasicCommentSuccessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IWorkCommentReplyPost {

    @FormUrlEncoded
    @POST("/v1/m/homewok/comments/save")
    Observable<BasicCommentSuccessBean> postWorkComment(@Field("userId") Integer userId,
                                                        @Field("content") String content,
                                                        @Field("refId") int refId,
                                                        @Field("pid") int pid,
                                                        @Field("toId") int toId,
                                                        @Field("toContent") String toContent,
                                                        @Field("replyId") int replyId);

}
