package com.jiyun.asmodeus.xyxy.contract;


import com.jiyun.asmodeus.xyxy.model.entity.HomeValuanleBean;

public interface ValiableContract {
    interface ValiablePresenter{
        void GetWorkZhiNeng();
        void GetWorkZanShu();
        void GetWorkZuiXin();
    }
    interface ValiableView{
        void laodWorkZhiNeng(HomeValuanleBean workBean);
        void laodWorkZanShu(HomeValuanleBean workBean);
        void laodWorkZuiXin(HomeValuanleBean workBean);
    }
}
