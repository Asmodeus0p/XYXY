package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.BasicSuccessBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IPostUserInfoService {

    @FormUrlEncoded
    @POST("/v1/m/user/info/edit")
    Observable<BasicSuccessBean> postUserInfo(@Field("id") Integer id,
                                              @Field("nickname") String nickname,
                                              @Field("realname") String realname,
                                              @Field("photo") String photo,
                                              @Field("images") String images,
                                              @Field("intro") String intro,
                                              @Field("details") String details,
                                              @Field("sex") int sex,
                                              @Field("birthday") String birthday,
                                              @Field("address") String address);
}
