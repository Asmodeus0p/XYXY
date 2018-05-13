package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.UserInfoBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserInfoService {



        @FormUrlEncoded
        @POST("/v1/m/user/info")
        Observable<UserInfoBean> getUserInfo(@Field("loginUserId") Integer loginUserId);


}
