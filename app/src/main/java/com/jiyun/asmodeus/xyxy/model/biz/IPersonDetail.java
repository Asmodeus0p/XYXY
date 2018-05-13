package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.PersonDetailBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPersonDetail {

    @FormUrlEncoded
    @POST("/v1/m/user/personal/homepage")
    Observable<PersonDetailBean> getPersonDetail(@Field("studentId") int studentId,
                                                 @Field("loginUserId") Integer loginUserId);
}
