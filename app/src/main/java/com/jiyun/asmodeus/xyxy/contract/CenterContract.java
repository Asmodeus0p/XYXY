package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.CenterBean;
import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;

public interface CenterContract {

    interface CenterPresenter {
        void getCenter();
    }
    interface VenterView{
        void getCenterPassBean(CenterBean centerBean);
    }
}
