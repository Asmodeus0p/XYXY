package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.AttentionListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ITeacherAttention {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/attention")
    Observable<AttentionListBean> getTeacherAttention(@Field("teacherId") int teacherId,
                                                      @Field("loginUserId") Integer loginUserId,
                                                      @Field("page") int page);
}
