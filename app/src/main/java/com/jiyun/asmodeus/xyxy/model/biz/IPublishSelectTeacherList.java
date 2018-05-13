package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.PublishSelectTeacherModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPublishSelectTeacherList {

    @FormUrlEncoded
    @POST("/v1/m/homewok/teacher")
    Observable<PublishSelectTeacherModel> getSelectTeacher(@Field("page") int page);
}
