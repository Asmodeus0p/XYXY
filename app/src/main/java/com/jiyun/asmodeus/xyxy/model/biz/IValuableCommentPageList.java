package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.ValuableDetailBeanl;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IValuableCommentPageList {

    @FormUrlEncoded
    @POST("/v1/m/artcircle/comments")
    Observable<ValuableDetailBeanl> getValuableList(@Field("artcircleId") int artcircleId,
                                                    @Field("loginUserId") Integer loginUserId,
                                                    @Field("page") int page);
}
