package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.PublicWorkBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPublishRedactWokDetail {

    @FormUrlEncoded
    @POST("/v1/m/homewok/save")
    Observable<PublicWorkBean> publishRedactWok(@Field("studentId") Integer studentId,
                                                @Field("majorIds") String majorIds,
                                                @Field("content") String content,
                                                @Field("path") String path,
                                                @Field("worksType") String worksType,
                                                @Field("permission") Integer permission,
                                                @Field("price") Double price,
                                                @Field("source") String source,
                                                @Field("teacherId") Integer teacherId,
                                                @Field("orderNo") String orderNo);
}
