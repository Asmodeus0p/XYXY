package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.MasterDetailWorkBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMasterDetailWork {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/homewok")
    Observable<MasterDetailWorkBean> getMasterDetailWork(@Field("teacherId") int teacherId,
                                                         @Field("page") int page);
}
