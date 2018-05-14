package com.jiyun.asmodeus.xyxy.model.biz;

import com.jiyun.asmodeus.xyxy.model.entity.NoticeDetailOrderModel;
import com.jiyun.asmodeus.xyxy.model.entity.OrderNoBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface AliPayService {

    @FormUrlEncoded
    @POST("/v1/m/order/save/recharge")
    Observable<NoticeDetailOrderModel> getOrder(@Field("buyerId") int buyerId,
                                                @Field("price") double price,
                                                @Field("amount") int amount,@HeaderMap Map<String,String> headers);

    @FormUrlEncoded
    @POST("/v1/m/alipay/params")
    Observable<OrderNoBean> getOrderNo(@HeaderMap Map<String,String> headres,@FieldMap Map<String,String> params);


}
