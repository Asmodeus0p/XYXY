package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.HomeWokListBean;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPersonInfoWok {

    @FormUrlEncoded
    @POST("/v1/m/user/personal/homepage/homewok")
    Observable<WorkBean> getWokList(@Field("loginUserId") Integer loginUserId,
                                    @Field("studentId") int studentId,
                                    @Field("page") int page);
}
