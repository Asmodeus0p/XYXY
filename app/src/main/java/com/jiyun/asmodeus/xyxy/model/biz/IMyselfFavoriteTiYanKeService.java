package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.HomeNoticeBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IMyselfFavoriteTiYanKeService {

    @FormUrlEncoded
    @POST("/v1/m/user/my/favorites")
    Observable<HomeNoticeBean> getFavoriteTiYanKe(@Field("loginUserId") Integer loginUserId,
                                              @Field("type") int type,
                                              @Field("page") int page);
}
