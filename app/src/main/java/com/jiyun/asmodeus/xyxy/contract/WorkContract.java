package com.jiyun.asmodeus.xyxy.contract;
import com.jiyun.asmodeus.xyxy.model.entity.WorkBean;

public interface WorkContract {
    interface WorkPresenter{
        void laodWorkDatas();
    }
    interface WorkView{
        void laodWorkZhiNeng(WorkBean workBean);
        void laodWorkTouTing(WorkBean workBean);
        void laodWorkDianPing(WorkBean workBean);
    }
}
