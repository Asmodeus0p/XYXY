package com.jiyun.asmodeus.xyxy.model.biz;


import com.jiyun.asmodeus.xyxy.model.entity.GiftListModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IGiftList {
    @FormUrlEncoded
    @POST("/v1/m/gift/list")
    Observable<GiftListModel> getGiftList(@Field("loginUserId") Integer loginUserId);
}
