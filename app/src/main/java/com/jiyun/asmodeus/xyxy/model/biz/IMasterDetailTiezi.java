package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by vicoltree on 17/11/21.
 */

public interface IMasterDetailTiezi {

    @FormUrlEncoded
    @POST("/v1/m/user/teacher/artcircle")
    Observable<HomeValuanleBean> geMasterDetailTieZi(@Field("loginUserId") Integer loginUserId,
                                                     @Field("teacherId") int teacherId,
                                                     @Field("page") int page);
}
