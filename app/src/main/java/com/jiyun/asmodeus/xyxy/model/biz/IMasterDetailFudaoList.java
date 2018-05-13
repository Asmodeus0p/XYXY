package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;



public interface IMasterDetailFudaoList {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/answer")
    Observable<WorkBean> getMasterDetailFudao(@Field("loginUserId") Integer loginUserId,
                                              @Field("teacherId") int teacherId,
                                              @Field("page") int page);
}
