package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.SystemMessageListBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MessageListService {

    @FormUrlEncoded
    @POST("/v1/m/message/list")
    Observable<SystemMessageListBean> getMessageList(@Field("loginUserId") Integer loginUserId);
}
