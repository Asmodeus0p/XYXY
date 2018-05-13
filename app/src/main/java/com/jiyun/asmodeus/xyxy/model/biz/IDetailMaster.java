package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IDetailMaster {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/homepage")
    Observable<MasterDetailBean> getMasterDetail(@Field("teacherId") int teacherId,
                                                 @Field("loginUserId") Integer loginUserId);
}
