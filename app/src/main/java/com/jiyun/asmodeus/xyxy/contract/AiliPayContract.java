package com.jiyun.asmodeus.xyxy.contract;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeDetailOrderModel;
import com.jiyun.asmodeus.xyxy.model.entity.OrderNoBean;

public interface AiliPayContract {

        interface getOrderNoPresenter{
            void sendOrderNo(int logindId,double price,int amount);
            void getOrderNo(String orderNo);
        }

    interface getOrderNoView{
        void getOrderNoBean(NoticeDetailOrderModel noticeDetailOrderModel);
        void getOrderNoB(OrderNoBean orderNoBean);
    }

}
