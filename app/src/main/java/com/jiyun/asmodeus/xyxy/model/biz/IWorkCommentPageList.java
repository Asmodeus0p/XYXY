package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.WokDetailBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface IWorkCommentPageList {

    @FormUrlEncoded
    @POST("/v1/m/homewok/comments")
    Observable<WokDetailBean> workCommentPage(@Field("homewokId") int homewokId,
                                              @Field("loginUserId") Integer loginUserId,
                                              @Field("page") int page);

}
