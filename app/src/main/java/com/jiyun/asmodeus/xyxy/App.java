package com.jiyun.asmodeus.xyxy;

import android.app.Application;
import android.content.Context;
import android.util.Config;

import com.jiyun.asmodeus.xyxy.model.entity.PreferenceListBean;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.List;

public class App extends Application {
    public static Context context;
    //当前app是否还存活判断
    private boolean isLaunch = false;


    private String orderNo;

    /**
     * 发布作业订单
     */
    private String workOrder;

    /**
     * 支付偷听跳转id
     */
    private int work_id;

    /**
     * 发布作业支付
     */
    private boolean isPay = false;

    /**
     * 支付重播地址
     */
    private String chongBoUrl;

    //专业类型
    private List<PreferenceListBean.DataBean.MajorsBean> majorsBeen;



    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(this,"5ab0bf46f43e483f930001aa"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        PlatformConfig.setQQZone("1106725573", "y0E1ICunHKwxfjFM");
        //58edcfeb310c93091c000be2 5965ee00734be40b580001a0


    }

    public List<PreferenceListBean.DataBean.MajorsBean> getMajorsBeen() {
        return majorsBeen;
    }

    public void setMajorsBeen(List<PreferenceListBean.DataBean.MajorsBean> majorsBeen) {
        this.majorsBeen = majorsBeen;
    }

    public boolean isLaunch() {
        return isLaunch;
    }

    public void setLaunch(boolean launch) {
        isLaunch = launch;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getWork_id() {
        return work_id;
    }

    public void setWork_id(int work_id) {
        this.work_id = work_id;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getChongBoUrl() {
        return chongBoUrl;
    }

    public void setChongBoUrl(String chongBoUrl) {
        this.chongBoUrl = chongBoUrl;
    }


    public String getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(String workOrder) {
        this.workOrder = workOrder;
    }
}
