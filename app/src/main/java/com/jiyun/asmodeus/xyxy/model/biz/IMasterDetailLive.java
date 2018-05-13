package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.LivingListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMasterDetailLive {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/live")
    Observable<LivingListBean> getMasterDetailLive(@Field("loginUserId") Integer loginUserId,
                                                   @Field("teacherId") int teacherId,
                                                   @Field("page") int page);
}
