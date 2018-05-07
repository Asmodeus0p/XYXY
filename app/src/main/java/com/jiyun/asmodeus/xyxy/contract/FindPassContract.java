package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.FindPassBean;
import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;

public interface FindPassContract {

    interface FindPassPresenter{
        void getFindPass(String userId,String code);
    }
    interface FindPassView{
        void getFindPassBean(FindPassBean loginRegsterSucessBean);
    }
}
