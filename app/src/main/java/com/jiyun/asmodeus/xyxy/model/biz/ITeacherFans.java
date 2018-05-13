package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.AttentionListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITeacherFans {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/fans")
    Observable<AttentionListBean> getTeacherFans(@Field("teacherId") int teacherId,
                                                 @Field("loginUserId") Integer loginUserId,
                                                 @Field("page") int page);
}
