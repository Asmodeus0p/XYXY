package com.jiyun.asmodeus.xyxy.contract;

import com.jiyun.asmodeus.xyxy.model.entity.LoginRegsterSucessBean;
import com.jiyun.asmodeus.xyxy.model.entity.NoticeBean;

import okhttp3.ResponseBody;

public interface LoginSucessContract {

    interface LoginPresenter{
        void laodLoginDatas(String userId,String pass);
    }
    interface LoginView{
        void laodLoginDatas(LoginRegsterSucessBean loginRegsterSucessBean);
    }
}
