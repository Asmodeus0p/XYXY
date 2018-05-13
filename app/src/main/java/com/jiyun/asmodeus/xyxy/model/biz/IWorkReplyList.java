package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.WorkReplyListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IWorkReplyList {

    @FormUrlEncoded
    @POST("/v1/m/homewok/reply")
    Observable<WorkReplyListBean> getWorkReplyList(@Field("userId") int userId,
                                                   @Field("commentsId") int commentsId,
                                                   @Field("homewokId") int homewokId,
                                                   @Field("loginUserId") Integer loginUserId,
                                                   @Field("page") int page);
}
